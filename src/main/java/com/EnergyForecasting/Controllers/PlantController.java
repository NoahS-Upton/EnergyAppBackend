package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Service.PlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value= "/plant")
public class PlantController {
    private final PlantService plantService;


    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Plant>> getAllPlants(){
        List<Plant> plants= plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Plant> addPlant(@RequestBody Plant p){
        Plant addedPlant= plantService.addPlant(p);
        return new ResponseEntity<>(addedPlant, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant p){
        Plant updatedPlant= plantService.updatePlant(p);
        return new ResponseEntity<>(updatedPlant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable("id") Long id){
        plantService.deletePlant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

