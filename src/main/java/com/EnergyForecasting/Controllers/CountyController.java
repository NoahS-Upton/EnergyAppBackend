package com.EnergyForecasting.Controllers;


import com.EnergyForecasting.Model.County;
import com.EnergyForecasting.Service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//controller for sending county information to and from front end
@CrossOrigin("*")
@RestController
@RequestMapping("/county")
public class CountyController {
    private final CountyService countyService;

    //constructor
    @Autowired
    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }
    //gets all counties
    @GetMapping("/all")
    public ResponseEntity<List<County>> getAllCounties(){
        List<County> counties= countyService.getAllCounties();
        return new ResponseEntity<>(counties, HttpStatus.OK);
    }
    //adds new county
    @PostMapping("/add")
    public ResponseEntity<County> addCounty(String county){
        County addedCounty= countyService.addCounty(county);
        return new ResponseEntity<County>(addedCounty, HttpStatus.CREATED);
    }
    //updates an existing county
    @PutMapping("/update")
    public ResponseEntity<County> updateCounty(@RequestBody County c){
        County updatedCounty= countyService.updateCounty(c);
        return new ResponseEntity<>(updatedCounty, HttpStatus.OK);
    }
    //deletes an existing county
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlantByCountyID(@PathVariable("id") Long countyID){
        countyService.deleteByCountyID(countyID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}