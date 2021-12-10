package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.ForecastOutputtNotFoundException;
import com.EnergyForecasting.Model.ForecastOutput;
import com.EnergyForecasting.Repository.ForecastOutputRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/*
service for retrieving saved forecast results from database
 */

@Service
@Transactional
public class ForecastOutputService {
    private ForecastOutputRepo forecastOutputRepo;

    //constructor
    @Autowired
    public ForecastOutputService(ForecastOutputRepo forecastOutputRepo) {
        this.forecastOutputRepo=forecastOutputRepo;
    }
    //get a specific forecast output from database by id
    public ForecastOutput getForecastById (Long outputID){
        return forecastOutputRepo.findByOutputID(outputID).orElseThrow(() -> new ForecastOutputtNotFoundException("Forecast output with ID=" + outputID + " not found"));
    }
    //saves a forecast output to database
    public ForecastOutput saveForecastOutput(ForecastOutput forecastOutput){
        return forecastOutputRepo.save(forecastOutput);
    }


}
