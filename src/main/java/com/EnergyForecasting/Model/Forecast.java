package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
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
    private RegionList regions;
    private CountyList counties;
    private boolean onshore;
    private boolean offshore;
    private boolean solar;
    private String userID;

    // Outputs to screen
    private CountyOutputs countyOutputs;

    //calculation variables
    private double[] WM2;
    private double[] windSpeed;
    private double[] windAngle;


    public Forecast() {
    }

    @Autowired
    public Forecast(boolean hourly, int days,RegionList region, CountyList county, boolean onshore, boolean offshore, boolean solar, String userID) {
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

    public RegionList getRegions() {
        return regions;
    }

    public void setRegions(RegionList regions) {
        this.regions = regions;
    }

    public CountyList getCounties() {
        return counties;
    }

    public void setCounties(CountyList counties) {
        this.counties = counties;
    }

    //getters and setters for forecast output variables
    public CountyOutputs getCountyOutputs() {
        return countyOutputs;
    }

    public void setCountyOutputs(CountyOutputs countyOutputs) {
        this.countyOutputs = countyOutputs;
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
                ", windSpeed=" + Arrays.toString(windSpeed) +
                ", windAngle=" + Arrays.toString(windAngle) +
                ", countyOutputs=" + countyOutputs +
                '}';
    }
}
