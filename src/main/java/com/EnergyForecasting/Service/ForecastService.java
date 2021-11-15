package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.ForecastNotFoundException;
import com.EnergyForecasting.Model.APICaller;
import com.EnergyForecasting.Model.Calculation;
import com.EnergyForecasting.Model.Forecast;
import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Repository.ForecastRepo;
import com.EnergyForecasting.Repository.PlantRepo;
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
    private PlantRepo plantRepo;
    private PlantService plantService;
    private Calculation calculation;
    private APICaller apiCaller;

    @Autowired
    public ForecastService(ForecastRepo forecastRepo, PlantRepo plantRepo, PlantService plantService) {
        this.forecastRepo = forecastRepo;
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
    public void deleteForecast (Long id) {
        forecastRepo.deleteForecastById(id);
    }

    public Forecast getForecastById (Long id){
        return forecastRepo.findForecastById(id).orElseThrow(() -> new ForecastNotFoundException("Forecast with ID=" + id + " not found"));
    }

    public ArrayList<Plant> getPlantByRegion(String region){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(region);
        return plants;
    }
    public ArrayList<Plant> getPlantByCounty(String county){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(county);
        return plants;
    }

    public void generateForecast(boolean hourly, int days, ArrayList<String> region, ArrayList<String> county, boolean onshore, boolean offshore, boolean solar, String userID) throws IOException, InterruptedException {
        Forecast forecast= new Forecast(hourly,days,region,county,onshore,offshore,solar,userID);
        ArrayList<Plant> plants = new ArrayList<Plant>();

        //for each region, gets each subsequent county for calling apis for calculation
        for (String r: region) {
            ArrayList<Plant> temp =getPlantByRegion(r);
            HashSet<String> set= new HashSet<>();
            for (Plant p: temp) {
                set.add(p.getCounty());
            }
            for (String s: set) {
                for (String c:county) {
                    if (!c.equals(s)){
                        county.add(s);
                    }
                }
            }


        //hashmaps for storing arrays of outputs for each county
        HashMap <String, ArrayList<Double>> offshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> onshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> solarMap= new HashMap <String, ArrayList<Double>>();


        //calls api for each county to allow for data to be used for calculations
        for (String c:county) {
            //test line
            System.out.println(c);

            ArrayList<Plant> countyPlants=getPlantByCounty(c);
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
            offshoreMap.put(c,offshoreProduction);
            onshoreMap.put(c,onshoreProduction);
            solarMap.put(c,solarProduction);
        }


        System.out.println("forecast generated");
        //forecastRepo.save(forecast);

        }
    }

    public void runForecast(Forecast forecast){}
}
