package com.EnergyForecasting.Service;

import com.EnergyForecasting.Model.APICaller;
import com.EnergyForecasting.Model.APIOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class APIService {

    public APIService() {
    }

    public APIOutput runByLongLat(String latLong) throws IOException, InterruptedException {
        APICaller apiCaller= new APICaller();
        apiCaller.getForecastDataByLatLong(latLong);
        APIOutput output= new APIOutput(
                apiCaller.getWindSpeed(),
                apiCaller.getWindDirDeg(),
                apiCaller.getWindGust(),
                apiCaller.getMaxWindSpeed(),
                apiCaller.getMinWindSpeed(),
                apiCaller.getSolarWM2(),
                apiCaller.getTemperature());
        return output;

    }

    public APIOutput runByCity(String cityCountry) throws IOException, InterruptedException {
        APICaller apiCaller= new APICaller();
        apiCaller.getForecastDataByCity(cityCountry);
        APIOutput output= new APIOutput(
                apiCaller.getWindSpeed(),
                apiCaller.getWindDirDeg(),
                apiCaller.getWindGust(),
                apiCaller.getMaxWindSpeed(),
                apiCaller.getMinWindSpeed(),
                apiCaller.getSolarWM2(),
                apiCaller.getTemperature());
        return output;
    }
}
