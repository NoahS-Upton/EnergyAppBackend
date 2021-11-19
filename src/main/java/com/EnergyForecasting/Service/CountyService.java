package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Model.County;
import com.EnergyForecasting.Repository.CountyRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountyService {
    private CountyRepo countyRepo;

    @Autowired
    public CountyService(CountyRepo countyRepo) {
        this.countyRepo = countyRepo;
    }

    public County saveCounty(County county){
        return countyRepo.save(county);
    }

    public List<County> getAllCounties() {
        return countyRepo.findAll();
    }

    public County updateCounty (County county){
        return countyRepo.save(county);
    }
    public void deleteByCountyID (Long countyID) {
        countyRepo.deleteByCountyID(countyID);
    }

    public County getByCountyID(Long countyID){
        return countyRepo.findByCountyID(countyID).orElseThrow(() -> new CountyNotFoundException("Forecast with ID=" + countyID + " not found"));
    }
}
