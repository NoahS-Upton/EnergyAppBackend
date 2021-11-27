package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Model.APIOutput;
import com.EnergyForecasting.Service.APIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@RequestMapping("/api")
public class APIController {
    private APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/runLongLat/")
    public ResponseEntity<APIOutput> runLongLat(@RequestBody String longitude, String latitude) throws IOException, InterruptedException {
        apiService.runApiByLongLat(longitude, latitude);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/runCity/")
    public ResponseEntity<APIOutput> runCity(@RequestBody String city, String country) throws IOException, InterruptedException {
        apiService.runApiByCity(city, country);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
