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

    public Simulation advancedSimulation(Set<Region> regions, Set<County> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar){
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
        simulationRepo.save(sim);
        runSimulation(sim);
        return sim;
    }


    public SimulationOutput runSimulation(Simulation simulation) {
        Simulation sim = simulation;

        //instantiate variables for creating output entity
        ArrayList<String> counties=new ArrayList<String>();
        ArrayList<String> regions=new ArrayList<String>();
        int intervals=0;
        HashMap<String, ArrayList<Double>> solarOutputs = null;
        HashMap<String, ArrayList<Double>> offShoreOutputs=null;
        HashMap<String, ArrayList<Double>> onShoreOutputs=null;

        for (County c: sim.getCounties()) {
            counties.add(c.getCounty());
        }
        for (Region r: sim.getRegions()) {
            regions.add(r.getRegion());
        }
        if (sim.isHourly()){
            intervals=sim.getDays()*24;
        }else if(!sim.isHourly()){
            intervals=sim.getDays();
        }

        //total capacity for each energy type for each region
        double countyOnshoreCapacity = 0.0;
        double countyOffshoreCapacity = 0.0;
        double countySolarCapacity = 0.0;

        ArrayList<Double> onshoreProduction = new ArrayList<Double>();
        ArrayList<Double> offshoreProduction = new ArrayList<Double>();
        ArrayList<Double> solarProduction = new ArrayList<Double>();


        for (String s : counties) {
            ArrayList<Plant> countyPlants = getPlantByCounty(s);

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

            solarOutputs.put(s, solarProduction);
            offShoreOutputs.put(s, offshoreProduction);
            onShoreOutputs.put(s, onshoreProduction);

            //reset variables
            countyOnshoreCapacity = 0.0;
            countyOffshoreCapacity = 0.0;
            countySolarCapacity = 0.0;
            onshoreProduction.clear();
            offshoreProduction.clear();
            solarProduction.clear();
        }

            SimulationOutput simOut= new SimulationOutput(counties,regions, intervals, solarOutputs,offShoreOutputs, onShoreOutputs);
            simulationRepo.save(sim);
            return simOut;
    }


    //takes previous generated simulation and reruns it to return values to screen
    public SimulationOutput rerunSimulation(Long id){
        Simulation sim=getSimulationById(id);
       return runSimulation(sim);
    }
}


