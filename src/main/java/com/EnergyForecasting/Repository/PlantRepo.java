package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepo extends JpaRepository<Plant, Long> {
    void deletePlantById(Long id);

    Optional<Plant> findPlantById(Long id);


}
