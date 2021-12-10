package com.EnergyForecasting.Service;

import com.EnergyForecasting.Repository.SimDaylightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//service to retrieve simulation input daylight hours
@Service
@Transactional
public class SimDaylightService {
    private SimDaylightRepo simDaylightRepo;

    //constructor
    @Autowired
    public SimDaylightService(SimDaylightRepo simDaylightRepo) {
        this.simDaylightRepo = simDaylightRepo;
    }


}
