package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name = "forecast_regions")
public class RegionList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Long forecastID;
    private ArrayList<String> regionList;

    @Autowired
    public RegionList(Long forecastID, ArrayList<String> regionList) {
        this.forecastID = forecastID;
        this.regionList = regionList;
    }

    public RegionList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getForecastID() {
        return forecastID;
    }

    public void setForecastID(Long forecastID) {
        this.forecastID = forecastID;
    }

    public ArrayList<String> getRegionList() {
        return regionList;
    }

    public void setRegionList(ArrayList<String> regionList) {
        this.regionList = regionList;
    }
}
