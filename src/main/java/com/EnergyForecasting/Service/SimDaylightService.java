package com.EnergyForecasting.Service;

import com.EnergyForecasting.Repository.SimDaylightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SimDaylightService {
    private SimDaylightRepo simDaylightRepo;

    @Autowired
    public SimDaylightService(SimDaylightRepo simDaylightRepo) {
        this.simDaylightRepo = simDaylightRepo;
    }


}
