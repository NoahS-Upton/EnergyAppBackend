package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.ForecastOutput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForecastOutputRepo extends JpaRepository<ForecastOutput, Long> {
    void deleteByOutputID(Long outputID);

    Optional<ForecastOutput> findByOutputID(Long outputID);

}
