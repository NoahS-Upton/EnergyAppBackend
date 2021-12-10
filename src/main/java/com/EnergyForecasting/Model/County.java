package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//used for the creation of county entities in the database
@Entity
@Table(name = "counties")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name="countyid")
    private Long countyID;
    private String county;

    //links to associated forecasts
    @JsonIgnore
    @ManyToMany(mappedBy = "forecastCounties")
    private Set<Forecast> forecastSet= new HashSet<>();
    //links to associated simulations
    @JsonIgnore
    @ManyToMany(mappedBy = "simulationCounties")
    private Set<Simulation> simulationSet= new HashSet<>();

    //constructors
    @Autowired
    public County(String county) {
        this.county = county;
    }
    public County() {
    }

    //getters and setters
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
