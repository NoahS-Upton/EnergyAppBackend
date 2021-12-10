package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.ForecastOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//repository for forecast outputs
@Repository
public interface ForecastOutputRepo extends JpaRepository<ForecastOutput, Long> {
    void deleteByOutputID(Long outputID);

    Optional<ForecastOutput> findByOutputID(Long outputID);

}
