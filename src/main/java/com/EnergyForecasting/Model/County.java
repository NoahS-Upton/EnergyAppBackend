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
    @Column(nullable = false, updatable = false)
    private Long countyID;
    private String county;

    @ManyToMany(mappedBy = "forecastCounties")
    Set<Forecast> forecastSet= new HashSet<>();

    @Autowired
    public County(Long countyID, String county) {
        this.countyID = countyID;
        this.county = county;
    }


    public County() {
    }

    public Long getCountyID() {
        return countyID;
    }

    public void setCountyID(Long id) {
        this.countyID = id;
    }
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
