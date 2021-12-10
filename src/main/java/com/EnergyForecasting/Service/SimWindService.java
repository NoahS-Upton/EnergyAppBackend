package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.SimulationInputNotFoundException;
import com.EnergyForecasting.Model.SimWind;
import com.EnergyForecasting.Repository.SimWindRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//service to get wind inputs
@Service
@Transactional
public class SimWindService {
    private SimWindRepo simWindRepo;
    //constructor
    @Autowired
    public SimWindService(SimWindRepo simWindRepo) {
        this.simWindRepo = simWindRepo;
    }
    //gets wind input from its id
    public SimWind getByWindID(Long windID) {
        return simWindRepo.findByWindID(windID).orElseThrow(() -> new SimulationInputNotFoundException("Simulation with ID=" + windID + " not found"));
    }
}
