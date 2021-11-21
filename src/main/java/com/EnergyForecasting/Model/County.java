package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "counties")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name="countyid")
    private Long countyID;
    private String county;

    @ManyToMany(mappedBy = "forecastCounties")
    Set<Forecast> forecastSet= new HashSet<>();

    @ManyToMany(mappedBy = "simulationCounties")
    Set<Simulation> simulationSet= new HashSet<>();

    @Autowired
    public County(String county) {
        this.county = county;
    }


    public County() {
    }

    public Long getCountyID() {
        return countyID;
    }

    public void setCountyID(Long countyID) {
        this.countyID = countyID;
    }
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Set<Forecast> getForecastSet() {
        return forecastSet;
    }

    public void setForecastSet(Set<Forecast> forecastSet) {
        this.forecastSet = forecastSet;
    }

    public Set<Simulation> getSimulationSet() {
        return simulationSet;
    }

    public void setSimulationSet(Set<Simulation> simulationSet) {
        this.simulationSet = simulationSet;
    }
}
