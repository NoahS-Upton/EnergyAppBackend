package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

//repository for counties
@Repository
@Transactional
public interface CountyRepo extends JpaRepository<County, Long> {
    void deleteByCountyID(Long countyID);

    Optional<County> findByCountyID(Long countyID);
}
