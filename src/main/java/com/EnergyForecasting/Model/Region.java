package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "regionid")
    private Long regionID;
    private String region;

    @JsonIgnore
    @ManyToMany(mappedBy = "forecastRegions")
    private Set<Forecast> forecastSet= new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "simulationRegions")
    private Set<Simulation> simulationSet= new HashSet<>();

    @Autowired
    public Region(String region) {
        this.region = region;
    }

    public Region() {
    }

    public Long getRegionID() {
        return regionID;
    }

    public void setRegionID(Long regionID) {
        this.regionID = regionID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
