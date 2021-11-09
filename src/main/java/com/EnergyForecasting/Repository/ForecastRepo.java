package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForecastRepo extends JpaRepository<Forecast, Long> {
    void deleteForecastById(Long id);

    Optional<Forecast> findForecastById(Long id);

}
