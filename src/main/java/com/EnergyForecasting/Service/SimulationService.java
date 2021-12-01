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
    public County findByCountyID(Long countyID) {
        return countyRepo.findByCountyID(countyID).orElseThrow(() -> new CountyNotFoundException("County with ID=" + countyID + " not found"));
    }
    public Region findByRegionID(Long regionID) {
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Region with ID=" + regionID + " not found"));
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
        ArrayList<Plant> plants=plantService.getPlantsByCounty(county);
        return plants;
    }

    public Simulation advancedSimulation(Set<Region> regions, Set<County> counties, int days, boolean hourly, ArrayList<SimDaylight> daylight, ArrayList<SimWind> windSpeed, boolean wind, boolean solar){
        Simulation sim= new Simulation(regions,counties,days,hourly,daylight,windSpeed,wind,solar);

        //gets all counties in regions and appends to county list for calculations

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
        HashMap<String, ArrayList<Double>> solarOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> offshoreOutputs = new HashMap<String, ArrayList<Double>>();
        HashMap<String, ArrayList<Double>> onshoreOutputs = new HashMap<String, ArrayList<Double>>();

        //takes county/region name and appends to array for output
        for (County c: sim.getCounties()) {
            counties.add(c.getCounty());
        }
        for (Region r: sim.getRegions()) {
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
//            System.out.println(countyPlants.get(0).getName());
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

            for (SimWind w :sim.getWindSpeed()) {
                if ((w.getValue() < 25 && w.getValue()> 5 && sim.isWind())) {
                    offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity,w.getValue()));
                    onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity, w.getValue()));
                } else {
                    offshoreProduction.add(0.00);
                    onshoreProduction.add(0.00);
                }
            }
            for (SimDaylight d:sim.getDaylightHours()) {
                if (sim.isSolar()) {
                    solarProduction.add(calculation.solarOutput(countySolarCapacity,d.getValue()));
                }
            }

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

            System.out.println(onshoreOutputs.get(s).get(0));

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


