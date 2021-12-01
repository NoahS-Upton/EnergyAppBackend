package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Simulation {
    
    //ID to track simulation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    //stats on region being simulated
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "simulationRegions",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "regionid")
    )
    private Set<Region> simulationRegions= new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "simulationCounties",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "countyid")
    )
    private Set<County> simulationCounties= new HashSet<>();


    //simulation criteria/constraints
    private int days;
    private boolean hourly;
    private boolean wind;
    private boolean solar;


    private ArrayList<SimDaylight> daylightHours;
    private ArrayList<SimWind> windSpeed;

    //no args alt constructor
    public Simulation() {
    }
    @Autowired
    public Simulation( Set<Region> regions, Set<County> counties, int days, boolean hourly, ArrayList<SimDaylight> daylightHours, ArrayList<SimWind> windSpeed, boolean wind, boolean solar) {
        //inputs from user
        this.simulationRegions = regions;
        this.simulationCounties= counties;
        this.days = days;
        this.hourly = hourly;
        this.daylightHours = daylightHours;
        this.windSpeed = windSpeed;
        this.wind = wind;
        this.solar = solar;
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

    public ArrayList<SimDaylight> getDaylightHours() {
        return daylightHours;
    }

    public void setDaylightHours(ArrayList<SimDaylight> daylightHours) {
        this.daylightHours = daylightHours;
    }

    public ArrayList<SimWind> getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(ArrayList<SimWind> windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Set<Region> getSimulationRegions() {
        return simulationRegions;
    }

    public void setSimulationRegions(Set<Region> simulationRegions) {
        this.simulationRegions = simulationRegions;
    }

    public Set<County> getSimulationCounties() {
        return simulationCounties;
    }

    public void setSimulationCounties(Set<County> simulationCounties) {
        this.simulationCounties = simulationCounties;
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

    public void assignCounty(County county) {
        simulationCounties.add(county);
    }

    public void assignRegion(Region region) {
        simulationRegions.add(region);
    }
}
