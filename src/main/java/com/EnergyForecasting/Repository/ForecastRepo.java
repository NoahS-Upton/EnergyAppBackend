package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//repository for forecasts
@Repository
public interface ForecastRepo extends JpaRepository<Forecast, Long> {
    void deleteForecastById(Long id);

    Optional<Forecast> findForecastById(Long id);

}
