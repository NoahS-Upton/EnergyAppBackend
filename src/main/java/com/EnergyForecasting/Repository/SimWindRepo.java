package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimWind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimWindRepo extends JpaRepository<SimWind, Long> {
    void deleteByWindID(Long windID);

    Optional<SimWind> findByWindID(Long windID);

}
