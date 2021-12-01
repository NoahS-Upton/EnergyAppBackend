package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimDaylight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimDaylightRepo extends JpaRepository<SimDaylight, Long> {
    void deleteByDaylightID(Long daylightID);

    Optional<SimDaylight> findByDaylightID(Long daylightID);

}
