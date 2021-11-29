package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimulationWindspeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimWindRepo extends JpaRepository<SimulationWindspeed, Long> {
    void deleteByWindID(Long windID);
    Optional<SimulationWindspeed> findByWindID(Long windID);
}
