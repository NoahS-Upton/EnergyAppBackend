package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long regionID;
    private  String region;

    @ManyToMany(mappedBy = "forecastRegions")
    private Set<Forecast> forecastSet= new HashSet<>();

    @Autowired
    public Region(Long regionID, String region) {
        this.regionID = regionID;
        this.region = region;
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
}
