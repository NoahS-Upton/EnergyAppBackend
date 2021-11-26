package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.ForecastOutputtNotFoundException;
import com.EnergyForecasting.Model.ForecastOutput;
import com.EnergyForecasting.Repository.ForecastOutputRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ForecastOutputService {
    private ForecastOutputRepo forecastOutputRepo;

    @Autowired
    public ForecastOutputService(ForecastOutputRepo forecastOutputRepo) {
        this.forecastOutputRepo=forecastOutputRepo;
    }

    public ForecastOutput getForecastById (Long outputID){
        return forecastOutputRepo.findByOutputID(outputID).orElseThrow(() -> new ForecastOutputtNotFoundException("Forecast output with ID=" + outputID + " not found"));
    }
}
