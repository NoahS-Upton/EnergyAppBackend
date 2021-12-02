package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Exceptions.ForecastNotFoundException;
import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Repository.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ForecastService {
    private ForecastRepo forecastRepo;
    private CountyRepo countyRepo;
    private RegionRepo regionRepo;
    private ForecastOutputRepo forecastOutputRepo;
    private ForecastOutputService forecastOutputService;
    private PlantRepo plantRepo;
    private PlantService plantService;
    private Calculation calculation;
    private APICaller apiCaller;

    @Autowired
    public ForecastService(ForecastRepo forecastRepo, CountyRepo countyRepo, RegionRepo regionRepo, ForecastOutputService forecastOutputService, PlantRepo plantRepo, PlantService plantService) {
        this.forecastRepo = forecastRepo;
        this.countyRepo = countyRepo;
        this.regionRepo = regionRepo;
        this.forecastOutputService = forecastOutputService;
        this.plantRepo = plantRepo;
        this.plantService = plantService;
        this.calculation = new Calculation();
        this.apiCaller = new APICaller();
    }

    public Forecast saveForecast(Forecast forecast){
        return forecastRepo.save(forecast);
    }

    public List<Forecast> getAllForecasts() {
        return forecastRepo.findAll();
    }

    public Forecast updateForecast (Forecast forecast){
        return forecastRepo.save(forecast);
    }
    public void deleteForecast (Long forecastID) {
        forecastRepo.deleteForecastById(forecastID);
    }


    public Forecast getForecastById (Long id){
        return forecastRepo.findForecastById(id).orElseThrow(() -> new ForecastNotFoundException("Forecast with ID=" + id + " not found"));
    }

    public List<Plant> getPlantByRegion(String region){
        List<Plant> plants=plantService.getPlantsByRegion(region);
        return plants;
    }
    public ArrayList<Plant> getPlantByCounty(String county){
        ArrayList<Plant> plants=plantService.getPlantsByCounty(county);
        return plants;
    }
    public County findByCountyID(Long countyID) {
        return countyRepo.findByCountyID(countyID).orElseThrow(() -> new CountyNotFoundException("County with ID=" + countyID + " not found"));
    }
    public Region findByRegionID(Long regionID) {
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Region with ID=" + regionID + " not found"));
    }

    public ForecastToScreen runForecast(@NonNull Forecast forecast) throws IOException, InterruptedException {
        //used for creating forecast outputs to display to the screen
        ArrayList<String> counties=new ArrayList<String>();
        ArrayList<String> regions=new ArrayList<String>();
        int intervals=0;
        HashMap<Integer, ArrayList<Double>> solarOutputs = new HashMap<Integer, ArrayList<Double>>();
        HashMap<Integer, ArrayList<Double>> offshoreOutputs = new HashMap<Integer, ArrayList<Double>>();
        HashMap<Integer, ArrayList<Double>> onshoreOutputs = new HashMap<Integer, ArrayList<Double>>();

        //creates array for storing forecastouputs for each county to be added into fts
        ArrayList<ForecastOutput> forecastOutputs= new ArrayList<ForecastOutput>();
        //creates container to send data to front end for forecast, in forecastToScreen
        HashMap<String,ArrayList<ForecastOutput>> fts= new HashMap<String,ArrayList<ForecastOutput>>();

        //for holding plants for calculation
        ArrayList<Plant> plants = new ArrayList<Plant>();
        HashSet<String> set= new HashSet<>();

        for (County c:forecast.getForecastCounties()) {
                counties.add(c.getCounty());
        }
        //for each region, gets each subsequent county
        for (Region r: forecast.getForecastRegions()) {//cycles through regions
            List<Plant> temp =getPlantByRegion(r.getRegion());//gets all plants by region
            //cycles through plants in region getting unique county names
            for (Plant p: temp ) {
                if (p.getCounty()!=null){
                    set.add(p.getCounty());
                }
            }
        }
        for (String s: set) {
            if(!counties.contains(s)){
                counties.add(s);
                ;
            }
        }

        //calculates increments of simulation
        if (forecast.isHourly()){
            intervals=forecast.getDays()*24;
        }else if(!forecast.isHourly()){
            intervals=forecast.getDays();
        }

        //total capacity for each energy type for each region
        double countyOnshoreCapacity = 0.0;
        double countyOffshoreCapacity = 0.0;
        double countySolarCapacity = 0.0;

        double countyOnshoreProduction=0.0;
        double countyOffshoreProduction=0.0;
        double countySolarProduction=0.0;

        double windspeed=0.0;
        double wm2=0.0;

        Calculation calculation= new Calculation();
        APICaller apiCaller=new APICaller();
        plants.clear();


        //geographical hold variables for api call
        String latitude=null;
        String longitude=null;
        String latChar= null;
        String longChar= null;




        for (String c: counties) {
            //gets all plants in county needed for calculation, as well as taking lat/long etc. from this plant
            plants = plantService.getPlantsByCounty(c);
            Plant plant = plants.get(0);
            latitude = plant.getLatitude();
            longitude = plant.getLongitude();
            latChar = plant.getLatChar();
            longChar = plant.getLongChar();


            apiCaller.getForecastData(forecast.isHourly(), forecast.getDays(), longitude, latitude, longChar, latChar);

            for (int i = 0; i < intervals; i++) {
                //gets capacities for calculations
                for (Plant p : plants) {
                    if (p.getType().equalsIgnoreCase("onshore")) {
                        countyOnshoreCapacity += p.getCapacity();
                    } else if (p.getType().equalsIgnoreCase("offshore")) {
                        countyOffshoreCapacity += p.getCapacity();
                    } else if (p.getType().equalsIgnoreCase("solar")) {
                        countyOffshoreCapacity += p.getCapacity();
                    }
                }

                windspeed = Double.parseDouble(apiCaller.getWindSpeed().get(i));
                wm2 = Double.parseDouble(apiCaller.getSolarWM2().get(i));

                countyOffshoreProduction = calculation.windOutput(countyOffshoreCapacity, windspeed);
                countyOnshoreProduction = calculation.windOutput(countyOnshoreCapacity, windspeed);
                countySolarProduction = calculation.solarOutput(countySolarCapacity, wm2);


                ForecastOutput forecastOutput = new ForecastOutput( i, c, countyOffshoreProduction, countyOnshoreProduction, countySolarProduction, forecast);
                //forecastOutputService.saveForecastOutput(forecastOutput);
                forecast.getCountyOutputs().add(forecastOutput);
                forecastOutputs.add(forecastOutput);
            }

            fts.put(c,forecastOutputs);

            //reset all necessary variable
            plants.clear();
            apiCaller.getWindSpeed().clear();
            apiCaller.getSolarWM2().clear();
            countyOnshoreCapacity = 0.0;
            countyOffshoreCapacity = 0.0;
            countySolarCapacity = 0.0;

            countyOnshoreProduction = 0.0;
            countyOffshoreProduction = 0.0;
            countySolarProduction = 0.0;

            windspeed = 0.0;
            wm2 = 0.0;
        }
        ForecastToScreen out= new ForecastToScreen(counties,regions,intervals,fts);
        return out;
    }

    public ForecastToScreen rerunForecast(Long id) throws IOException, InterruptedException {
        Forecast forecast=getForecastById(id);
        return runForecast(forecast);
    }
}
