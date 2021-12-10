package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.Region;
import com.EnergyForecasting.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//handles http requests from front end regarding regions
@CrossOrigin("*")
@RestController
@RequestMapping("/region")
public class RegionController {
        private final RegionService regionService;
        //constructor
        @Autowired
        public RegionController(RegionService regionService) {
            this.regionService = regionService;
        }
        //gets all regions
        @GetMapping("/all")
        public ResponseEntity<List<Region>> getAllRegions(){
            List<Region> regions= regionService.getAllRegions();
            return new ResponseEntity<>(regions, HttpStatus.OK);
        }
        //adds a new region
        @PostMapping("/add")
        public ResponseEntity<Region> addRegion(String region){
            Region addedRegion= regionService.addRegion(region);
            return new ResponseEntity<Region>(addedRegion, HttpStatus.CREATED);
        }
        //updates existing regions
        @PutMapping("/update")
        public ResponseEntity<Region> updateRegion(@RequestBody Region r){
            Region updatedRegion= regionService.updateRegion(r);
            return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
        }
        //deletes an existing region
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deletePlantByRegionID(@PathVariable("id") Long regionID){
            regionService.deleteByRegionID(regionID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
}


