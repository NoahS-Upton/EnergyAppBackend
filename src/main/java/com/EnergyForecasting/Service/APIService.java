package com.EnergyForecasting.Service;

import com.EnergyForecasting.Model.APICaller;
import com.EnergyForecasting.Model.APIOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class APIService {

    public APIService() {
    }

    public APIOutput runApiByLongLat(String longitude, String latitude) throws IOException, InterruptedException {
        APICaller apiCaller= new APICaller();
        apiCaller.getForecastDataByLatLong(longitude, latitude);
        APIOutput output= new APIOutput(
                apiCaller.getWindSpeed(),
                apiCaller.getWindDirDeg(),
                apiCaller.getWindGust(),
                apiCaller.getMaxWindSpeed(),
                apiCaller.getMinWindSpeed(),
                apiCaller.getSolarWM2(),
                apiCaller.getAvgDewpointC(),
                apiCaller.getTemperature());
        return output;

    }

    public APIOutput runApiByCity(String city, String country) throws IOException, InterruptedException {
        APICaller apiCaller= new APICaller();
        apiCaller.getForecastDataByCity(city, country);
        APIOutput output= new APIOutput(
                apiCaller.getWindSpeed(),
                apiCaller.getWindDirDeg(),
                apiCaller.getWindGust(),
                apiCaller.getMaxWindSpeed(),
                apiCaller.getMinWindSpeed(),
                apiCaller.getSolarWM2(),
                apiCaller.getAvgDewpointC(),
                apiCaller.getTemperature());
        return output;
    }
}
