package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Exceptions.ForecastNotFoundException;
import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Repository.CountyRepo;
import com.EnergyForecasting.Repository.ForecastRepo;
import com.EnergyForecasting.Repository.PlantRepo;
import com.EnergyForecasting.Repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ForecastService {
    private ForecastRepo forecastRepo;
    private CountyRepo countyRepo;
    private RegionRepo regionRepo;
    private PlantRepo plantRepo;
    private PlantService plantService;
    private Calculation calculation;
    private APICaller apiCaller;

    @Autowired
    public ForecastService(ForecastRepo forecastRepo, CountyRepo countyRepo, RegionRepo regionRepo, PlantRepo plantRepo, PlantService plantService) {
        this.forecastRepo = forecastRepo;
        this.countyRepo = countyRepo;
        this.regionRepo = regionRepo;
        this.plantRepo = plantRepo;
        this.plantService = plantService;
        this.calculation = new Calculation();
        this.apiCaller = new APICaller();
    }

    public ForecastService() {
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



    public void generateForecast(boolean hourly, int days, Set<Region> region, Set<County> county, boolean onshore, boolean offshore, boolean solar, String userID) throws IOException, InterruptedException {
        Forecast forecast= new Forecast(hourly,days,region,county,onshore,offshore,solar,userID);
        ArrayList<Plant> plants = new ArrayList<Plant>();

        //for each region, gets each subsequent county for calling apis for calculation
        for (Region r: region) {
            List<Plant> temp =getPlantByRegion(r.getRegion());
            HashSet<String> set= new HashSet<>();
            for (Plant p: temp) {
                set.add(p.getCounty());
            }
            for (String s: set) {
                for (County c:county) {
                    if (!c.equals(s)){
                        county.add(c);
                    }
                }
            }


        //hashmaps for storing arrays of outputs for each county
        HashMap <String, ArrayList<Double>> offshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> onshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> solarMap= new HashMap <String, ArrayList<Double>>();


        //calls api for each county to allow for data to be used for calculations
        for (County c:county) {
            //test line
            System.out.println(c.getCounty());

            ArrayList<Plant> countyPlants=getPlantByCounty(c.getCounty());
            //gets api data for county
            apiCaller.getForecastData(hourly,days,
                    countyPlants.get(0).getLongitude(),countyPlants.get(0).getLatitude(),
                    countyPlants.get(0).getLongChar(), countyPlants.get(0).getLatChar());
            double countyOnshoreCapacity=0.0;
            double countyOffshoreCapacity=0.0;
            double countySolarCapacity=0.0;

            ArrayList<Double> onshoreProduction= new ArrayList<Double>();
            ArrayList<Double> offshoreProduction= new ArrayList<Double>();
            ArrayList<Double> solarProduction= new ArrayList<Double>();


            //gets capacities for calculations
            for (Plant p: countyPlants) {
                if (p.getType().equals("onshore")){
                countyOnshoreCapacity+=p.getCapacity();}
                else if (p.getType().equals("offShore")){
                    countyOffshoreCapacity+=p.getCapacity();
                }
                else if (p.getType().equals("solar")){
                    countyOffshoreCapacity+=p.getCapacity();
                }
            }



            ArrayList<Double> windSpeeds= new ArrayList<Double>();
            ArrayList<Double> solRad= new ArrayList<Double>();
            for (String t: apiCaller.getWindSpeed()){
                windSpeeds.add(Double.parseDouble(t));
            }
            for (String t: apiCaller.getSolarWM2()){
                solRad.add(Double.parseDouble(t));
            }

            for (Double ws: windSpeeds) {
                if (ws < 25 && ws > 5) {
                offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity,ws));
                onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity,ws));
                }else{
                    offshoreProduction.add(0.00);
                    onshoreProduction.add(0.00);
                }
            }
            for (Double sr:solRad) {
                solarProduction.add(calculation.solarOutput(countySolarCapacity,sr));
            }
            offshoreMap.put(c.getCounty(),offshoreProduction);
            onshoreMap.put(c.getCounty(),onshoreProduction);
            solarMap.put(c.getCounty(),solarProduction);
        }


        System.out.println("forecast generated");
        //forecastRepo.save(forecast);

        }
    }

    public void runForecast(Forecast forecast){}
}
