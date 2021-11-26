package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Exceptions.ForecastNotFoundException;
import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Repository.*;
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



    public ForecastToScreen runForecast(Forecast forecast) throws IOException, InterruptedException {
        ArrayList<String> counties=new ArrayList<String>();
        ArrayList<String> regions=new ArrayList<String>();
        int intervals=0;
        HashMap<String, ArrayList<Double>> solarOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> offshoreOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> onshoreOutputs = new HashMap<String, ArrayList<Double>>();

        ForecastToScreen fts=new ForecastToScreen();
        ArrayList<Plant> plants = new ArrayList<Plant>();
        HashSet<String> set= new HashSet<>();

        //for each region, gets each subsequent county for calling apis for calculation
        for (Region r: forecast.getForecastRegions()) {
            List<Plant> temp =getPlantByRegion(r.getRegion());
            for (Plant p: temp) {
                set.add(p.getCounty());
            }
            for (String s: set) {
                for (County c:forecast.getForecastCounties()) {
                    if (!c.getCounty().equals(s)){
                        counties.add(c.getCounty());
                    }
                }
            }
        }
        return fts;
    }

    public ForecastToScreen rerunForecast(Long id) throws IOException, InterruptedException {
        Forecast forecast=getForecastById(id);
        return runForecast(forecast);
    }
}
