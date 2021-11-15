package com.EnergyForecasting.Controllers;


import com.EnergyForecasting.Model.Forecast;
import com.EnergyForecasting.Service.ForecastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Forecast>> getAllForecasts(){
        List<Forecast> forecasts= forecastService.getAllForecasts();
        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    @GetMapping("/getForecast")
    public ResponseEntity<List<Forecast>> getForecastById(@PathVariable("id") Long id){
        Forecast forecast = forecastService.getForecastById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Forecast> addForecast(@RequestBody Forecast f){
        Forecast addedForecast= forecastService.saveForecast(f);
        return new ResponseEntity<>(addedForecast, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Forecast> updateForecast(@RequestBody Forecast f){
        Forecast updatedForecast= forecastService.updateForecast(f);
        return new ResponseEntity<>(updatedForecast, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForecast(@PathVariable("id") Long id){
        forecastService.deleteForecast(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/runForecast/{id}")
    public ResponseEntity<List<Forecast>> runForecast(@PathVariable("id") Long id){
        Forecast forecast = forecastService.getForecastById(id);
        forecastService.runForecast(forecast);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/generateForecast")
    public ResponseEntity<Forecast> generateForecast(){ ;

        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
