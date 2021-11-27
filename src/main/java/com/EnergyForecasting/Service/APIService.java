package com.EnergyForecasting.Service;

import com.EnergyForecasting.Model.APICaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIService {
    private APICaller apiCaller;

    @Autowired
    public APIService(APICaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    public void runApiByLongLat(){

    }

    public void runApiByCity(){


    }
}
