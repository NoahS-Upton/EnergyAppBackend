package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.SimulationNotFoundException;
import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Repository.CountyRepo;
import com.EnergyForecasting.Repository.PlantRepo;
import com.EnergyForecasting.Repository.RegionRepo;
import com.EnergyForecasting.Repository.SimulationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    public SimulationService(SimulationRepo simulationRepo, PlantRepo plantRepo, PlantService plantService, RegionRepo regionRepo, CountyRepo countyRepo) {
        this.simulationRepo = simulationRepo;
        this.plantRepo = plantRepo;
        this.plantService = plantService;
        this.regionRepo = regionRepo;
        this.countyRepo = countyRepo;
        this.calculation = new Calculation();
    }

    public Simulation saveSimulation(Simulation simulation){
        return simulationRepo.save(simulation);
    }

    public List<Simulation> getAllSimulations() {
        return simulationRepo.findAll();
    }

    public Simulation updateSimulation (Simulation simulation){
        return simulationRepo.save(simulation);
    }
    public void deleteSimulationById (Long id) {
        simulationRepo.deleteSimulationById(id);
    }

    public Simulation getSimulationById(Long id) {
        return simulationRepo.findSimulationById(id).orElseThrow(() -> new SimulationNotFoundException("Simulation with ID=" + id + " not found"));
    }
    public ArrayList<Plant> getAllPlants(){
        ArrayList<Plant> plants= (ArrayList<Plant>) plantService.getAllPlants();
        return plants;
    }

    public ArrayList<Plant> getPlantByRegion(String region){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(region);
        return plants;
    }
    public ArrayList<Plant> getPlantByCounty(String county){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(county);
        return plants;
    }

    public Simulation generateSimulation(Set<Region> regions, Set<County> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar){
        Simulation sim= new Simulation(regions,counties,days,hourly,wm2,windSpeed,wind,solar);

        //gets all counties in regions and appends to county list for calculations
        for (Region r: regions) {
            ArrayList<Plant> temp =getPlantByRegion(r.getRegion());
            HashSet<String> set= new HashSet<>();
            for (Plant p: temp) {
                set.add(p.getCounty());
            }
            for (String s: set) {
                for (County c:counties) {
                    if (!c.getCounty().equals(s)){
                        counties.add(c);
                    }
                }
            }
        }
//        //hashmaps for storing arrays of outputs for each county
//        HashMap<String, ArrayList<Double>> offshoreMap= new HashMap <String, ArrayList<Double>>();
//        HashMap <String, ArrayList<Double>> onshoreMap= new HashMap <String, ArrayList<Double>>();
//        HashMap <String, ArrayList<Double>> solarMap= new HashMap <String, ArrayList<Double>>();
//
//        //total capacity for each energy type for each region
//        double countyOnshoreCapacity=0.0;
//        double countyOffshoreCapacity=0.0;
//        double countySolarCapacity=0.0;
//
//        ArrayList<Double> onshoreProduction= new ArrayList<Double>();
//        ArrayList<Double> offshoreProduction= new ArrayList<Double>();
//        ArrayList<Double> solarProduction= new ArrayList<Double>();
//
//        for (String c: counties) {
//
//            ArrayList<Plant> countyPlants=getPlantByCounty(c);
//
//            //gets capacities for calculations
//             for (Plant p: countyPlants) {
//                if (p.getType().equals("onshore")){
//                    countyOnshoreCapacity+=p.getCapacity();}
//                else if (p.getType().equals("offShore")){
//                    countyOffshoreCapacity+=p.getCapacity();
//                }
//                else if (p.getType().equals("solar")){
//                    countyOffshoreCapacity+=p.getCapacity();
//                }
//            }
//
//
//            if (windSpeed < 25 && windSpeed > 5 && wind==true) {
//                offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity,windSpeed));
//                onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity,windSpeed));
//            }else{
//                offshoreProduction.add(0.00);
//                onshoreProduction.add(0.00);
//            }
//            if(solar==true) {
//                solarProduction.add(calculation.solarOutput(countySolarCapacity, wm2));
//            }
//            offshoreMap.put(c,offshoreProduction);
//            onshoreMap.put(c,onshoreProduction);
//            solarMap.put(c,solarProduction);
//
//            sim.setSolarOutput(solarMap);
//            sim.setOffshoreOutput(offshoreMap);
//            sim.setOnshoreOutput(onshoreMap);
//        }
        simulationRepo.save(sim);
        return sim;
    }


    public void runSimulation(Simulation simulation) {
        Simulation sim = simulation;
        //gets all counties in regions and appends to county list for calculations
        ArrayList<Plant> allPlants= getAllPlants();
        if (sim.getRegions()==null){
            HashSet<String> set = new HashSet<>();
            for (Plant p : allPlants) {
                set.add(p.getCounty());
            }
            for (String s : set) {
                for (County c : sim.getCounties()) {
                    if (!c.getCounty().equals(s)) {
                        sim.getCounties().add(c);
                    }
                }
            }
        }else{
        for (Region r : sim.getRegions()) {
            ArrayList<Plant> temp = getPlantByRegion(r.getRegion());
            HashSet<String> set = new HashSet<>();
            for (Plant p : temp) {
                set.add(p.getCounty());
            }
            for (String s : set) {
                for (County c : sim.getCounties()) {
                    if (!c.getCounty().equals(s)) {
                        sim.getCounties().add(c);
                    }
                }
            }
        }
        }

        //hashmaps for storing arrays of outputs for each county
        HashMap<String, ArrayList<Double>> offshoreMap = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> onshoreMap = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> solarMap = new HashMap<String, ArrayList<Double>>();

        //total capacity for each energy type for each region
        double countyOnshoreCapacity = 0.0;
        double countyOffshoreCapacity = 0.0;
        double countySolarCapacity = 0.0;

        ArrayList<Double> onshoreProduction = new ArrayList<Double>();
        ArrayList<Double> offshoreProduction = new ArrayList<Double>();
        ArrayList<Double> solarProduction = new ArrayList<Double>();

        for (County c : sim.getCounties()) {

            ArrayList<Plant> countyPlants = getPlantByCounty(c.getCounty());

            //gets capacities for calculations
            for (Plant p : countyPlants) {
                if (p.getType().equals("onshore")) {
                    countyOnshoreCapacity += p.getCapacity();
                } else if (p.getType().equals("offShore")) {
                    countyOffshoreCapacity += p.getCapacity();
                } else if (p.getType().equals("solar")) {
                    countyOffshoreCapacity += p.getCapacity();
                }
            }
            if ((sim.getWindSpeed() < 25 && sim.getWindSpeed() > 5 && sim.isWind())) {
                offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity, sim.getWindSpeed()));
                onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity, sim.getWindSpeed()));
            } else {
                offshoreProduction.add(0.00);
                onshoreProduction.add(0.00);
            }
            if (sim.isSolar()) {
                solarProduction.add(calculation.solarOutput(countySolarCapacity, sim.getWM2()));
            }
            offshoreMap.put(c.getCounty(), offshoreProduction);
            onshoreMap.put(c.getCounty(), onshoreProduction);
            solarMap.put(c.getCounty(), solarProduction);

            sim.setSolarOutput(solarMap);
            sim.setOffshoreOutput(offshoreMap);
            sim.setOnshoreOutput(onshoreMap);
        }
        simulationRepo.save(sim);
    }

}


