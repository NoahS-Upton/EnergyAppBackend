package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.Region;
import com.EnergyForecasting.Repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegionService {
    private RegionRepo regionRepo;

    @Autowired
    public RegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    public Region saveCounty(Region region){
        return regionRepo.save(region);
    }

    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

    public Region updateRegion(Region region){
        return regionRepo.save(region);
    }
    public void deleteByRegionID(Long regionID) {
        regionRepo.deleteByRegionID(regionID);
    }

    public Region getByRegionID(Long regionID){
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Forecast with ID=" + regionID + " not found"));
    }
}
