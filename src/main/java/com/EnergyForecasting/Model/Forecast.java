package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Forecast implements Serializable {
    //input variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private boolean hourly;
    private int days;
    private ArrayList<String> regions;
    private ArrayList<String> counties;
    private boolean onshore;
    private boolean offshore;
    private boolean solar;
    private String userID;

    // Outputs to screen
    private double[] regionWindOutput;
    private double[] regionSolarOutput;

    //calculation variables
    private double[] WM2;
    private double[] windSpeed;
    private double[] windAngle;
    private ArrayList<CountyOutputs> countyOutputs;

    public Forecast() {
    }

    @Autowired
    public Forecast(boolean hourly, int days,ArrayList<String>region, ArrayList<String> county, boolean onshore, boolean offshore, boolean solar, String userID) {
        this.hourly = hourly;
        this.days = days;
        this.regions = region;
        this.counties = county;
        this.onshore = onshore;
        this.offshore = offshore;
        this.solar = solar;
        this.userID=userID;
    }

    //getters and setters for retrieving forecast(input variables)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isHourly() {
        return hourly;
    }
    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public boolean isOnshore() {
        return onshore;
    }
    public void setOnshore(boolean onshore) {
        this.onshore = onshore;
    }
    public boolean isOffshore() {
        return offshore;
    }
    public void setOffshore(boolean offshore) {
        this.offshore = offshore;
    }
    public boolean isSolar() {
        return solar;
    }
    public void setSolar(boolean solar) {
        this.solar = solar;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public ArrayList<String> getRegions() {
        return regions;
    }
    public void setRegions(ArrayList<String> region) {
        this.regions = region;
    }
    public ArrayList<String> getCounties() {
        return counties;
    }
    public void setCounties(ArrayList<String> counties) {
        this.counties = counties;
    }

    //getters and setters for forecast output variables
    public double[] getRegionWindOutput() {
        return regionWindOutput;
    }
    public void setRegionWindOutput(double[] regionWindOutput) {
        this.regionWindOutput = regionWindOutput;
    }
    public double[] getRegionSolarOutput() {
        return regionSolarOutput;
    }
    public void setRegionSolarOutput(double[] regionSolarOutput) {
        this.regionSolarOutput = regionSolarOutput;
    }

    public double[] getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double[] windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double[] getWindAngle() {
        return windAngle;
    }
    public void setWindAngle(double[] windAngle) {
        this.windAngle = windAngle;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", hourly=" + hourly +
                ", days=" + days +
                ", region=" + regions +
                ", county=" + counties +
                ", onshore=" + onshore +
                ", offshore=" + offshore +
                ", solar=" + solar +
                ", userID='" + userID + '\'' +
                ", regionWindOutput=" + Arrays.toString(regionWindOutput) +
                ", regionSolarOutput=" + Arrays.toString(regionSolarOutput) +
                ", windSpeed=" + Arrays.toString(windSpeed) +
                ", windAngle=" + Arrays.toString(windAngle) +
                ", countyOutputs=" + countyOutputs +
                '}';
    }
}
