package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Service.SimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
// controller for passing simulations and related info from and to front end
@RestController
@RequestMapping("simulation")
public class SimulationController {
    private final SimulationService simulationService;

    //constructor
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }
    //gets all simulations
    @GetMapping("/all")
    public ResponseEntity<List<Simulation>> getAllSimulations(){
        List<Simulation> simulations= simulationService.getAllSimulations();
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }
    //get simulation by id
    @GetMapping("/getSimulation/{id}")
    public ResponseEntity<List<Simulation>> getSimulationById(@PathVariable("id") Long id){
        Simulation simulation = simulationService.getSimulationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //reruns pre saved simulation by simulation id
    @GetMapping("/rerun/{id}")
    public ResponseEntity<SimulationOutput> rerunSimulation(@PathVariable("id") Long id){
        SimulationOutput simulationOutput=simulationService.rerunSimulation(id);
        return new ResponseEntity<>(simulationOutput,HttpStatus.OK);
    }
    //adds a new simulation entity
    @PostMapping("/add")
    public ResponseEntity<Simulation> addSimulation(@RequestBody Simulation s){
        Simulation addedSimulation= simulationService.saveSimulation(s);
        return new ResponseEntity<Simulation>(addedSimulation, HttpStatus.CREATED);
    }
    //updates an existing simulation
    @PutMapping("/update")
    public ResponseEntity<Simulation> updateSimulation(@RequestBody Simulation s){
        Simulation updatedSimulation= simulationService.updateSimulation(s);
        return new ResponseEntity<>(updatedSimulation, HttpStatus.OK);
    }
    //deletes an existing simulation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSimulation(@PathVariable("id") Long id){
        simulationService.deleteSimulationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //passes an empty simulation entity to front end
    @GetMapping("/null")
    public ResponseEntity<Simulation> getNullSimulation(){
        Simulation simulation= new Simulation();
        return new ResponseEntity<Simulation>(simulation, HttpStatus.OK);
    }
    //passes an empty simulation output entity to front end
    @GetMapping("/nullOut")
    public ResponseEntity<SimulationOutput> getNullSimulationOutput(){
        SimulationOutput simOut= new SimulationOutput();
        return new ResponseEntity<SimulationOutput>(simOut, HttpStatus.OK);
    }

    //runs simulation
    @GetMapping("/runSimulation")
    public ResponseEntity<SimulationOutput> runSimulation(@RequestBody Simulation simulation){
        SimulationOutput  simulationOutput=simulationService.runSimulation(simulation);
        return new ResponseEntity<>(simulationOutput, HttpStatus.OK);
    }
    //creates an advanced simulation
    @PostMapping("/advancedSimulation")
    public ResponseEntity<Simulation> advancedSimulation(@RequestBody Set<Region> regions, Set<County> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar){
        simulationService.advancedSimulation(regions,counties,days,hourly, wm2,windSpeed,wind, solar);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    //assigns counties to simulation
    @PutMapping("/{simulationID}/county/{countyID}")
    public Simulation assignCountyToSimulation(@PathVariable Long simulationID, @PathVariable Long countyID){
        Simulation simulation = simulationService.getSimulationById(simulationID);
        County county=simulationService.findByCountyID(countyID);
        simulation.assignCounty(county);
        return simulationService.saveSimulation(simulation);
    }
    //assigns regions to simulation
    @PutMapping("/{simulationID}/region/{regionID}")
    public Simulation assignRegionToSimulation(@PathVariable Long simulationID, @PathVariable Long regionID){
        Simulation simulation = simulationService.getSimulationById(simulationID);
        Region region=simulationService.findByRegionID(regionID);
        simulation.assignRegion(region);
        return simulationService.saveSimulation(simulation);
    }

    //creates new simulation from specific inputs
    @GetMapping("/addByValues/{days}/{hourly}/{solar}/{wind}/{windspeed}/{daylight}")
    public ResponseEntity<Simulation>rerunForecast(@PathVariable int days,@PathVariable boolean hourly,
                                                 @PathVariable boolean solar, @PathVariable boolean wind,
                                                 @PathVariable double windspeed,@PathVariable int daylight
    ) {
        Simulation simulation= new Simulation(null, null, days,hourly, daylight, windspeed, wind, solar);
        simulationService.saveSimulation(simulation);
        return new ResponseEntity<>(simulation, HttpStatus.OK);
    }
}
