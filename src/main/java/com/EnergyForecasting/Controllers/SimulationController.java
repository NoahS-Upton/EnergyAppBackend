package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.Simulation;
import com.EnergyForecasting.Service.SimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Simulation")
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Simulation>> getAllSimulations(){
        List<Simulation> simulations= simulationService.getAllSimulations();
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }

    @GetMapping("/getSimulation")
    public ResponseEntity<List<Simulation>> getSimulationById(@PathVariable("id") Long id){
        Simulation simulation = simulationService.getSimulationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Simulation> addSimulation(@RequestBody Simulation s){
        Simulation addedSimulation= simulationService.saveSimulation(s);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Simulation> updateSimulation(@RequestBody Simulation s){
        Simulation updatedSimulation= simulationService.updateSimulation(s);
        return new ResponseEntity<>(updatedSimulation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSimulation(@PathVariable("id") Long id){
        simulationService.deleteSimulationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/runSimulation/{id}")
    public ResponseEntity<List<Simulation>> runSimulation(@PathVariable("id") Long id){
        Simulation simulation = simulationService.getSimulationById(id);
        simulationService.runSimulation(simulation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/generateSimulation")
    public ResponseEntity<Simulation> generateSimulation(){ ;

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}
