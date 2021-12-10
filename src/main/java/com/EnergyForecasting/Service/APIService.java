package com.EnergyForecasting.Service;

import com.EnergyForecasting.Model.APICaller;
import com.EnergyForecasting.Model.APIOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/*
Service for managing calling weather api independently and passing on the retrieved data
 */

@Service
public class APIService {
    //constructor
    @Autowired
    public APIService() {
    }

    //used to call api and get data from a given longitude and latitude
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

    //used to call api and get data from a given city
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
