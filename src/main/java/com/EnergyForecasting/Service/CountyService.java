package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.CountyNotFoundException;
import com.EnergyForecasting.Model.County;
import com.EnergyForecasting.Repository.CountyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


/*
service for manipulating and retrieving county information in database
 */
@Service
@Transactional
public class CountyService {
    private CountyRepo countyRepo;

    //constructor
    @Autowired
    public CountyService(CountyRepo countyRepo) {
        this.countyRepo = countyRepo;
    }

    //adds new county to repository
    public County addCounty(String county){
        County coun=new County(county);
        return countyRepo.save(coun);
    }
    //gets all counties from repo
    public List<County> getAllCounties() {
        return countyRepo.findAll();
    }
    //updates existing county
    public County updateCounty (County county){
        return countyRepo.save(county);
    }

    //deletes an existing county
    public void deleteByCountyID (Long countyID) {
        countyRepo.deleteByCountyID(countyID);
    }

    //gets a specific county from database using its id
    public County getCountyByCountyID(Long countyID){
        return countyRepo.findByCountyID(countyID).orElseThrow(() -> new CountyNotFoundException("County with ID=" + countyID + " not found"));
    }
}
