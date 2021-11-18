package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Forecast;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ForecastRegionRepo {
    void deleteForecastRegionById(Long id);

    Optional<Forecast> findForecastRegionById(Long id);
}
