package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Forecast;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CountyRepo {
    void deleteForecastCountyById(Long id);

    Optional<Forecast> findForecastCountyById(Long id);
}
