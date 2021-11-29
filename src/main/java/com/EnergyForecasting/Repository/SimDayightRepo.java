package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimulationDaylight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
interface SimDaylightRepo extends JpaRepository<SimulationDaylight, Long> {

    void deleteByDaylightID(Long daylightID);
    Optional<SimulationDaylight> findByDaylightID(Long daylightID);
}