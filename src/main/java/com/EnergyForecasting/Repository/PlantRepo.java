package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.Plant;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//repository for plants
@Repository
@Transactional
public interface PlantRepo extends JpaRepository<Plant, Long> {
    void deletePlantById(Long id);

    Optional<Plant> findPlantById(@NonNull Long id);



}
