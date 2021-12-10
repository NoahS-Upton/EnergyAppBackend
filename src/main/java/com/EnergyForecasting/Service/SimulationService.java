package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Exceptions.SimulationNotFoundException;
import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Repository.CountyRepo;
import com.EnergyForecasting.Repository.PlantRepo;
import com.EnergyForecasting.Repository.RegionRepo;
import com.EnergyForecasting.Repository.SimulationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/*
service for managing all simulations in the system
 */
@Service
@Transactional
@Slf4j
public class SimulationService {
    private SimulationRepo simulationRepo;
    private PlantRepo plantRepo;
    private PlantService plantService;
    private Calculation calculation;
    private RegionRepo regionRepo;
    private CountyRepo countyRepo;

    //constructor
    @Autowired
    public SimulationService(SimulationRepo simulationRepo, PlantRepo plantRepo, PlantService plantService, RegionRepo regionRepo, CountyRepo countyRepo) {
        this.simulationRepo = simulationRepo;
        this.plantRepo = plantRepo;
        this.plantService = plantService;
        this.regionRepo = regionRepo;
        this.countyRepo = countyRepo;
        this.calculation = new Calculation();
    }
    //saves a new simulation
    public Simulation saveSimulation(Simulation simulation){
        return simulationRepo.save(simulation);
    }
    //gets all previously run simulations
    public List<Simulation> getAllSimulations() {
        return simulationRepo.findAll();
    }
    //updates existing simulation
    public Simulation updateSimulation (Simulation simulation){
        return simulationRepo.save(simulation);
    }
    //deletes existing simulation
    public void deleteSimulationById (Long id) {
        simulationRepo.deleteSimulationById(id);
    }
    //gets individual simulation from its id
    public Simulation getSimulationById(Long id) {
        return simulationRepo.findSimulationById(id).orElseThrow(() -> new SimulationNotFoundException("Simulation with ID=" + id + " not found"));
    }
    //gets specific county
    public County findByCountyID(Long countyID) {
        return countyRepo.findByCountyID(countyID).orElseThrow(() -> new CountyNotFoundException("County with ID=" + countyID + " not found"));
    }
    //gets specific region
    public Region findByRegionID(Long regionID) {
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Region with ID=" + regionID + " not found"));
    }
    //gets all plants
    public ArrayList<Plant> getAllPlants(){
        ArrayList<Plant> plants= (ArrayList<Plant>) plantService.getAllPlants();
        return plants;
    }
    //gets plants in a specific region
    public ArrayList<Plant> getPlantByRegion(String region){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(region);
        return plants;
    }
    //gets plants in a specific county
    public ArrayList<Plant> getPlantByCounty(String county){
        ArrayList<Plant> plants=plantService.getPlantsByCounty(county);
        return plants;
    }
    //runs advanced simulation
    public Simulation advancedSimulation(Set<Region> regions, Set<County> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar){
        Simulation sim= new Simulation(regions,counties,days,hourly,wm2,windSpeed,wind,solar);
        //gets all counties in regions and appends to county list for calculations
        simulationRepo.save(sim);
        runSimulation(sim);
        return sim;
    }
    //runs simulation
    public SimulationOutput runSimulation(Simulation simulation) {
        Simulation sim = simulation;
        //instantiate variables for creating output entity
        ArrayList<String> counties=new ArrayList<String>();
        ArrayList<String> regions=new ArrayList<String>();
        int intervals=0;
        HashMap<String, ArrayList<Double>> solarOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> offshoreOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> onshoreOutputs = new HashMap<String, ArrayList<Double>>();
        //takes county/region name and appends to array for output
        for (County c: sim.getSimulationCounties()) {
            counties.add(c.getCounty());
        }
        for (Region r: sim.getSimulationRegions()) {
            regions.add(r.getRegion());
        }
        HashSet<String> set= new HashSet<>();
        //additional loop to convert regions into to composite counties
        for (String r: regions) {//cycles through regions
            ArrayList<Plant> temp =getPlantByRegion(r);//gets all plants by region
            System.out.println(temp.size());
            for (Plant p: temp ) {//cycles through plants in region getting unique county names
                if (p.getCounty()!=null){
                    set.add(p.getCounty());

                }
            }
        }
        for (String s: set) {
            if(!counties.contains(s)){
                counties.add(s);
            }
        }
        //calculates increments of simulation
        if (sim.isHourly()){
            intervals=sim.getDays()*24;
        }else if(!sim.isHourly()){
            intervals=sim.getDays();
        }
        //total capacity for each energy type for each region
        double countyOnshoreCapacity = 0.0;
        double countyOffshoreCapacity = 0.0;
        double countySolarCapacity = 0.0;
        //variable for storing the calculated values of energy production
        ArrayList<Double> onshoreProduction = new ArrayList<Double>();
        ArrayList<Double> offshoreProduction = new ArrayList<Double>();
        ArrayList<Double> solarProduction = new ArrayList<Double>();
        for (String s : counties) {
            ArrayList<Plant> countyPlants = getPlantByCounty(s);
            //gets capacities for calculations
            for (Plant p : countyPlants) {
                if (p.getType().equalsIgnoreCase("onshore")) {
                    countyOnshoreCapacity += p.getCapacity();
                } else if (p.getType().equalsIgnoreCase("offshore")) {
                    countyOffshoreCapacity += p.getCapacity();
                } else if (p.getType().equalsIgnoreCase("solar")) {
                    countyOffshoreCapacity += p.getCapacity();
                }
            }
            if (sim.isWind()) {
                offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity, sim.getWindSpeed()));
                onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity, sim.getWindSpeed()));
            } else {
                offshoreProduction.add(0.00);
                onshoreProduction.add(0.00);
            }
            if (sim.isSolar()) {
                solarProduction.add(calculation.solarHourlyOutput(countySolarCapacity, sim.getDaylightHours()));
            }
            System.out.println(onshoreProduction.get(0));

            solarOutputs.put(s, new ArrayList<Double>());
            offshoreOutputs.put(s, new ArrayList<Double>());
            onshoreOutputs.put(s, new ArrayList<Double>());
            for (Double d: solarProduction) {
                solarOutputs.get(s).add(d);
            }
            for (Double d: offshoreProduction) {
                offshoreOutputs.get(s).add(d);
            }
            for (Double d: onshoreProduction) {
                onshoreOutputs.get(s).add(d);
            }

            //reset variables
            countyOnshoreCapacity = 0.0;
            countyOffshoreCapacity = 0.0;
            countySolarCapacity = 0.0;
            onshoreProduction.clear();
            offshoreProduction.clear();
            solarProduction.clear();
        }
        SimulationOutput simOut= new SimulationOutput(counties,regions, intervals, solarOutputs,offshoreOutputs, onshoreOutputs);
        simulationRepo.save(sim);
        return simOut;
    }
    //takes previous generated simulation and reruns it to return values to screen
    public SimulationOutput rerunSimulation(Long id){
        Simulation sim=getSimulationById(id);
        return runSimulation(sim);
    }
}

