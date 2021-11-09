package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.SimulationNotFoundException;
import com.EnergyForecasting.Model.Calculation;
import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Model.Simulation;
import com.EnergyForecasting.Repository.PlantRepo;
import com.EnergyForecasting.Repository.SimulationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class SimulationService {
    private SimulationRepo simulationRepo;
    private PlantRepo plantRepo;
    private PlantService plantService;
    private Calculation calculation;

    @Autowired
    public SimulationService(){
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

    public ArrayList<Plant> getPlantByRegion(String region){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(region);
        return plants;
    }
    public ArrayList<Plant> getPlantByCounty(String county){
        ArrayList<Plant> plants=plantService.getPlantsByRegion(county);
        return plants;
    }

    public void generateSimulation(String simulationName, ArrayList<String> regions, ArrayList<String> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar){
        Simulation sim= new Simulation(simulationName,regions,counties,days,hourly,wm2,windSpeed,wind,solar);

        for (String r: regions) {
            ArrayList<Plant> temp =getPlantByRegion(r);
            HashSet<String> set= new HashSet<>();
            for (Plant p: temp) {
                set.add(p.getCounty());
            }
            for (String s: set) {
                for (String c:counties) {
                    if (!c.equals(s)){
                        counties.add(s);
                    }
                }
            }
        }
        //hashmaps for storing arrays of outputs for each county
        HashMap<String, ArrayList<Double>> offshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> onshoreMap= new HashMap <String, ArrayList<Double>>();
        HashMap <String, ArrayList<Double>> solarMap= new HashMap <String, ArrayList<Double>>();


        double countyOnshoreCapacity=0.0;
        double countyOffshoreCapacity=0.0;
        double countySolarCapacity=0.0;

        ArrayList<Double> onshoreProduction= new ArrayList<Double>();
        ArrayList<Double> offshoreProduction= new ArrayList<Double>();
        ArrayList<Double> solarProduction= new ArrayList<Double>();

        for (String c: counties) {

            ArrayList<Plant> countyPlants=getPlantByCounty(c);

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


            if (windSpeed < 25 && windSpeed > 5 && wind==true) {
                offshoreProduction.add(calculation.windOutput(countyOffshoreCapacity,windSpeed));
                onshoreProduction.add(calculation.windOutput(countyOnshoreCapacity,windSpeed));
            }else{
                offshoreProduction.add(0.00);
                onshoreProduction.add(0.00);
            }
            if(solar==true) {
                solarProduction.add(calculation.solarOutput(countySolarCapacity, wm2));
            }
            offshoreMap.put(c,offshoreProduction);
            onshoreMap.put(c,onshoreProduction);
            solarMap.put(c,solarProduction);
        }

        simulationRepo.save(sim);
    }
}


