package com.EnergyForecasting.Repository;

import com.EnergyForecasting.Model.SimWind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//repository for simulation wind inputs
@Repository
public interface SimWindRepo extends JpaRepository<SimWind, Long> {
    void deleteByWindID(Long windID);

    Optional<SimWind> findByWindID(Long windID);

}
