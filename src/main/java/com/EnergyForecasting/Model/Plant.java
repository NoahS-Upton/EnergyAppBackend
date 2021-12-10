package com.EnergyForecasting.Model;

import javax.persistence.*;
import java.io.Serializable;

//a plant entity used for calculations
@Entity
public class Plant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Double capacity;
    private String type;
    private String region;
    private String county;
    private String latChar;
    private String latitude;
    private String longChar;
    private String longitude;

    //constructors with various required inputs
    public Plant(String name,  Double capacity, String type, String region, String county) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.region = region;
        this.county = county;
    }

    public Plant(String name, int noOf, Double capacity, String type, String region, String county, String latChar, String latitude, String longChar, String longitude) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.region = region;
        this.county = county;
        this.latChar = latChar;
        this.latitude = latitude;
        this.longChar = longChar;
        this.longitude = longitude;
    }

    public Plant() {
    }


    //getters and setters
    public String getRegion() {
        return region;
    }
    public String getName() {
        return name;
    }
    public Double getCapacity() {
        return capacity;
    }
    public String getType() {
        return type;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public Long getId() {
        return id;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getLatChar() {
        return latChar;
    }
    public String getLongChar() {
        return longChar;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", latChar='" + latChar + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longChar='" + longChar + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
