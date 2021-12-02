package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.APIOutput;
import com.EnergyForecasting.Service.APIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/api")
public class APIController {
    private APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/runLongLat")
    public ResponseEntity<APIOutput> runByLatLong(@RequestBody String latLong) throws IOException, InterruptedException {
        apiService.runByLongLat(latLong);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/runCity")
    public ResponseEntity<APIOutput> runByCity(@RequestBody String city, String country) throws IOException, InterruptedException {
        apiService.runByCity(city, country);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
