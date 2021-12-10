package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//used to store and access region entities in database
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "regionid")
    private Long regionID;
    private String region;

    //links to associated forecasts
    @JsonIgnore
    @ManyToMany(mappedBy = "forecastRegions")
    private Set<Forecast> forecastSet= new HashSet<>();

    //links to associated simulations
    @JsonIgnore
    @ManyToMany(mappedBy = "simulationRegions")
    private Set<Simulation> simulationSet= new HashSet<>();

    //constructors
    @Autowired
    public Region(String region) {
        this.region = region;
    }
    public Region() {
    }


    //getters and setters
    public Long getRegionID() {
        return regionID;
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
    public Set<Simulation> getSimulationSet() {
        return simulationSet;
    }

}
