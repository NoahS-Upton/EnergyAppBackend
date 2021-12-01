package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.SimulationInputNotFoundException;
import com.EnergyForecasting.Model.SimWind;
import com.EnergyForecasting.Repository.SimWindRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SimWindService {
    private SimWindRepo simWindRepo;

    @Autowired
    public SimWindService(SimWindRepo simWindRepo) {
        this.simWindRepo = simWindRepo;
    }

    public SimWind getByWindID(Long windID) {
        return simWindRepo.findByWindID(windID).orElseThrow(() -> new SimulationInputNotFoundException("Simulation with ID=" + windID + " not found"));
    }


}
