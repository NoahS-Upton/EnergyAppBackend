package com.EnergyForecasting.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Plant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String region;
    private String name;
    private double capacity;
    private String type;
    private int noOf;
    private String county;
    private String latitude;
    private String longitude;
    private String latChar;
    private String longChar;

    public Plant() {
    }

    public Plant(String region, String name, double capacity, String type, int noOf, String county, String latitude, String longitude, String latChar, String longChar) {
        this.region = region;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.noOf=noOf;
        this.county=county;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latChar = latChar;
        this.longChar = longChar;
    }


    public String getRegion() {
        return region;
    }
    public String getName() {
        return name;
    }
    public double getCapacity() {
        return capacity;
    }
    public String getType() {
        return type;
    }
    public int getNoOf() {
        return noOf;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setNoOf(int noOf) {
        this.noOf = noOf;
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
                ", region='" + region + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", noOf=" + noOf +
                ", county='" + county + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latChar='" + latChar + '\'' +
                ", longChar='" + longChar + '\'' +
                '}';
    }
}
