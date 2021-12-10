package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.APIOutput;
import com.EnergyForecasting.Service.APIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
//handles http requests from front end for independent api calls
@RestController
@RequestMapping("/api")
public class APIController {
    private APIService apiService;
    //constructor
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }
    //gets weather data for a specific longitude, latitude location
    @GetMapping("/runLatLong/{latLong}")
    public ResponseEntity<APIOutput> runByLatLong(@PathVariable String latLong) throws IOException, InterruptedException {
        APIOutput apiOutput=apiService.runByLongLat(latLong);
        return new ResponseEntity<APIOutput>(apiOutput,HttpStatus.OK);
    }
    //gets weather data for a specific city location
    @GetMapping("/runCity/{cityCountry}")
    public ResponseEntity<APIOutput> runByCity(@PathVariable String cityCountry) throws IOException, InterruptedException {
        APIOutput apiOutput=apiService.runByCity(cityCountry);
        return new ResponseEntity<APIOutput>(apiOutput,HttpStatus.OK);
    }


}
