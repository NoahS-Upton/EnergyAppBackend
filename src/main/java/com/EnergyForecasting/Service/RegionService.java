package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.Region;
import com.EnergyForecasting.Repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/*
service for managing regions
 */

@Service
@Transactional
public class RegionService {
    private RegionRepo regionRepo;

    //constructor
    @Autowired
    public RegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    //adds new region
    public Region addRegion(String region){
        Region reg=new Region(region);
        return regionRepo.save(reg);
    }
    //gets all regions
    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }
    //updates existing region
    public Region updateRegion(Region region){
        return regionRepo.save(region);
    }
    //deletes existing region
    public void deleteByRegionID(Long regionID) {
        regionRepo.deleteByRegionID(regionID);
    }

    //gets region from its id
    public Region getRegionByRegionID(Long regionID){
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Region with ID=" + regionID + " not found"));
    }
}
