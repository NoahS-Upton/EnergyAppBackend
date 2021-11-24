package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.RegionNotFoundException;
import com.EnergyForecasting.Model.Region;
import com.EnergyForecasting.Repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class RegionService {
    private RegionRepo regionRepo;

    @Autowired
    public RegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    public Region addRegion(String region){
        Region reg=new Region(region);
        return regionRepo.save(reg);
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

    public Region getRegionByRegionID(Long regionID){
        return regionRepo.findByRegionID(regionID).orElseThrow(() -> new RegionNotFoundException("Region with ID=" + regionID + " not found"));
    }
}
