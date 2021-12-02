package com.EnergyForecasting.Service;

import com.EnergyForecasting.Model.APICaller;
import com.EnergyForecasting.Model.APIOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class APIService {

    public APIService() {
    }

    public APIOutput runByLongLat( String latLong) throws IOException, InterruptedException {
        APICaller apiCaller= new APICaller();
        String[] latlong= latLong.split(",");
        String longitude = latlong[1].strip();
        String latitude= latlong[0].strip();
        apiCaller.getForecastDataByLatLong(latitude, longitude);
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

    public APIOutput runByCity(String city, String country) throws IOException, InterruptedException {
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
