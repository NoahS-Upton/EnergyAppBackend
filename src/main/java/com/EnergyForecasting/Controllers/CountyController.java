package com.EnergyForecasting.Controllers;


import com.EnergyForecasting.Model.County;
import com.EnergyForecasting.Service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/county")
public class CountyController {
    private final CountyService countyService;

    @Autowired
    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<County>> getAllCounties(){
        List<County> countys= countyService.getAllCounties();
        return new ResponseEntity<>(countys, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<County> addCounty(County county){
        County addedCounty= countyService.addCounty(county);
        return new ResponseEntity<County>(addedCounty, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<County> updateCounty(@RequestBody County r){
        County updatedCounty= countyService.updateCounty(r);
        return new ResponseEntity<>(updatedCounty, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlantByCountyID(@PathVariable("id") Long countyID){
        countyService.deleteByCountyID(countyID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}