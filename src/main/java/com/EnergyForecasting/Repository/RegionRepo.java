package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//repository for regions
@Repository
@Transactional
public interface RegionRepo extends JpaRepository<Region, Long> {
    void deleteByRegionID(Long regionID);

    Optional<Region> findByRegionID(Long regionID);
}
