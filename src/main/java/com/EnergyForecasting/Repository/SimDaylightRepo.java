package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimDaylight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//repository for simulation daylight inputs
@Repository
public interface SimDaylightRepo extends JpaRepository<SimDaylight, Long> {
    void deleteByDaylightID(Long daylightID);

    Optional<SimDaylight> findByDaylightID(Long daylightID);

}
