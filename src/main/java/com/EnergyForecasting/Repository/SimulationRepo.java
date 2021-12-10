package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//repository for Simulations
@Repository
public interface SimulationRepo extends JpaRepository<Simulation,Long> {
   void deleteSimulationById(Long id);

    Optional<Simulation> findSimulationById(Long id) ;


}
