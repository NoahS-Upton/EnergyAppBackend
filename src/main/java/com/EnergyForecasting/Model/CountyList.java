package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
public class CountyList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Long forecastID;
    private ArrayList<String> countyList;

    @Autowired
    public CountyList(Long forecastID, ArrayList<String> countyList) {
        this.forecastID = forecastID;
        this.countyList = countyList;
    }

    public CountyList() {
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

    public ArrayList<String> getCountyList() {
        return countyList;
    }

    public void setCountyList(ArrayList<String> countyList) {
        this.countyList = countyList;
    }
}
