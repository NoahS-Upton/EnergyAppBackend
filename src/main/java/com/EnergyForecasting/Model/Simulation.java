package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class Simulation {
    //ID to track simulation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    //stats on region being simulated
    private ArrayList<String> regions;
    private ArrayList<String> counties;

    //energy outputs
    private HashMap solarOutput;
    private HashMap onshoreOutput;
    private HashMap offshoreOutput;

    //simulation criteria/constraints
    private int days;
    private boolean hourly;
    private double WM2;
    private double windSpeed;
    private boolean wind;
    private boolean solar;

    //no args alt constructor
    public Simulation() {
    }
    @Autowired
    public Simulation( ArrayList<String> regions, ArrayList<String> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar) {
        //inputs from user
        this.regions = regions;
        this.counties = counties;
        this.days = days;
        this.hourly = hourly;
        this.WM2 = wm2;
        this.windSpeed = windSpeed;
        this.wind = wind;
        this.solar = solar;
    }


    //getters and setters
    public HashMap getSolarOutput() {
        return solarOutput;
    }
    public void setSolarOutput(HashMap solarOutput) {
        this.solarOutput = solarOutput;
    }
    public HashMap getOnshoreOutput() {
        return onshoreOutput;
    }
    public void setOnshoreOutput(HashMap onshoreOutput) {
        this.onshoreOutput = onshoreOutput;
    }
    public HashMap getOffshoreOutput() {
        return offshoreOutput;
    }

    public void setOffshoreOutput(HashMap offshoreOutput) {
        this.offshoreOutput = offshoreOutput;
    }

    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public boolean isHourly() {
        return hourly;
    }
    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public boolean isWind() {
        return wind;
    }
    public void setWind(boolean wind) {
        this.wind = wind;
    }
    public boolean isSolar() {
        return solar;
    }
    public void setSolar(boolean solar) {
        this.solar = solar;
    }
    public double getWM2() {
        return WM2;
    }
    public void setWM2(double WM2) {
        this.WM2 = WM2;
    }
    public ArrayList<String> getRegions() {
        return regions;
    }
    public void setRegions(ArrayList<String> regions) {
        this.regions = regions;
    }
    public ArrayList<String> getCounties() {
        return counties;
    }
    public void setCounties(ArrayList<String> counties) {
        this.counties = counties;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
