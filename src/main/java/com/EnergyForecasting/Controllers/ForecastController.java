package com.EnergyForecasting.Controllers;


import com.EnergyForecasting.Model.*;
import com.EnergyForecasting.Service.CountyService;
import com.EnergyForecasting.Service.ForecastOutputService;
import com.EnergyForecasting.Service.ForecastService;
import com.EnergyForecasting.Service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastService forecastService;
    private final ForecastOutputService forecastOutputService;
    private final RegionService regionService;
    private final CountyService countyService;

    public ForecastController(ForecastService forecastService, ForecastOutputService forecastOutputService, RegionService regionService, CountyService countyService) {
        this.forecastService = forecastService;
        this.forecastOutputService = forecastOutputService;
        this.regionService = regionService;
        this.countyService = countyService;
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

    @GetMapping("/addByValues/{days}/{hourly}/{solar}/{offshore}/{onshore}/{user}")
    public ResponseEntity<Forecast>rerunForecast(@PathVariable int days,@PathVariable boolean hourly,
                                                         @PathVariable boolean solar, @PathVariable boolean offshore,
                                                         @PathVariable boolean onshore,@PathVariable String user
    ) {
        Forecast forecast= new Forecast(hourly, days,null,null,onshore,offshore,solar,user);
        forecastService.saveForecast(forecast);
        return new ResponseEntity<>(forecast, HttpStatus.OK);
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

    @GetMapping("/null")
    public ResponseEntity<Forecast> getNullForecast(){
        Forecast forecast= new Forecast();
        return new ResponseEntity<Forecast>(forecast, HttpStatus.OK);
    }

    @GetMapping("/runForecast")
    public ResponseEntity<ForecastToScreen> runForecast(@RequestBody Forecast forecast) throws IOException, InterruptedException {
        ForecastToScreen forecastToScreen= forecastService.runForecast(forecast);
        return new ResponseEntity<>(forecastToScreen, HttpStatus.OK);
    }

    @GetMapping("/rerun/{id}")
    public ResponseEntity<ForecastToScreen>rerunForecast(@PathVariable("id") Long id) throws IOException, InterruptedException {
       ForecastToScreen forecastToScreen=forecastService.rerunForecast(id);
        return new ResponseEntity<>(forecastToScreen,HttpStatus.OK);
    }

    @PostMapping("/generateForecast")
    public ResponseEntity<Forecast> generateForecast(){ ;

        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @PutMapping("/{forecastID}/county/{countyID}")
    public Forecast assignCountyToForecast(@PathVariable Long forecastID, @PathVariable Long countyID){
        Forecast forecast = forecastService.getForecastById(forecastID);
        County county=forecastService.findByCountyID(countyID);
        forecast.assignCounty(county);
        return forecastService.saveForecast(forecast);
    }

    @PutMapping("/{forecastID}/region/{regionID}")
    public Forecast assignRegionToForecast(@PathVariable Long forecastID, @PathVariable Long regionID){
        Forecast forecast = forecastService.getForecastById(forecastID);
        Region region=forecastService.findByRegionID(regionID);
        forecast.assignRegion(region);
        return forecastService.saveForecast(forecast);
    }

    @PutMapping("/{forecastID}/region/{region}")
    public Forecast assignRegionToForecastByName(@PathVariable Long forecastID, @PathVariable String region){
        Forecast forecast = forecastService.getForecastById(forecastID);
        List<Region> regions=regionService.getAllRegions();
        Region reg= new Region();
        for (Region r: regions) {
            if (r.getRegion().equals(region));
            reg= regionService.getRegionByRegionID(r.getRegionID());
        }
        forecast.assignRegion(reg);
        return forecastService.saveForecast(forecast);
    }

    @PutMapping("/{forecastID}/region/{county}")
    public Forecast assignCountyToForecastByName(@PathVariable Long forecastID, @PathVariable String county){
        Forecast forecast = forecastService.getForecastById(forecastID);
        List<County> counties=countyService.getAllCounties();
        County coun= new County();
        for (County c: counties) {
            if (c.getCounty().equals(county));
            coun= countyService.getCountyByCountyID(c.getCountyID());
        }
        forecast.assignCounty(coun);
        return forecastService.saveForecast(forecast);
    }

    @PutMapping("/{forecastID}/output/{outputID}")
    public Forecast assignForecastToOutput(@PathVariable Long forecastID, @PathVariable Long outputID){
        ForecastOutput forecastOutput = forecastOutputService.getForecastById(forecastID);
        Forecast forecast=forecastService.getForecastById(forecastID);
        forecastOutput.assignForecast(forecast);
        return forecastService.saveForecast(forecast);
    }

}
