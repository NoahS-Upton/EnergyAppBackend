package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Simulation {
    //ID to track simulation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    //stats on region being simulated
    @ManyToMany
    @JoinTable(
            name = "simulationRegions",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "regionid")
    )
    private Set<Region> simulationRegions= new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "simulationCounties",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "countyid")
    )
    private Set<County> simulationCounties= new HashSet<>();

    @ManyToOne
    @JoinTable()




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
    public Simulation( Set<Region> regions, Set<County> counties, int days, boolean hourly, double wm2, double windSpeed, boolean wind, boolean solar) {
        //inputs from user
        this.simulationRegions = regions;
        this.simulationCounties= counties;
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

    public Set<Region> getRegions() {
        return simulationRegions;
    }

    public void setRegions(Set<Region> simulationRegions) {
        this.simulationRegions = simulationRegions;
    }

    public Set<County> getCounties() {
        return simulationCounties;
    }

    public void setCounties(Set<County> simulationCounties) {
        this.simulationCounties = simulationCounties;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
