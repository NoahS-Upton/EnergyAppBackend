package com.EnergyForecasting.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Plant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private double capacity;
    private String type;
    private String region;
    private String county;
    private String latChar;
    private String latitude;
    private String longChar;
    private String longitude;

    public Plant(String name,  double capacity, String type, String region, String county) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.region = region;
        this.county = county;
    }

    public Plant(String name, int noOf, double capacity, String type, String region, String county, String latChar, String latitude, String longChar, String longitude) {
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
