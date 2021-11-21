package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.Region;
import com.EnergyForecasting.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/region")
public class RegionController {
        private final RegionService regionService;

        @Autowired
        public RegionController(RegionService regionService) {
            this.regionService = regionService;
        }

        @GetMapping("/all")
        public ResponseEntity<List<Region>> getAllRegions(){
            List<Region> regions= regionService.getAllRegions();
            return new ResponseEntity<>(regions, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<Region> addRegion(String region){
            Region addedRegion= regionService.addRegion(region);
            return new ResponseEntity<Region>(addedRegion, HttpStatus.CREATED);
        }

        @PutMapping("/update")
        public ResponseEntity<Region> updateRegion(@RequestBody Region r){
            Region updatedRegion= regionService.updateRegion(r);
            return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deletePlantByRegionID(@PathVariable("id") Long regionID){
            regionService.deleteByRegionID(regionID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
}


