package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "SimulationWindspeed")
public class SimulationWindspeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long windID;
    private double windspeed;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Simulation simulation;

    public SimulationWindspeed(double windspeed, Simulation simulation) {
        this.windspeed = windspeed;
        this.simulation = simulation;
    }
    public void assignSimulation(Simulation simulation) {
        this.simulation=simulation;
    }


    public Long getWindID() {
        return windID;
    }

    public void setWindID(Long windID) {
        this.windID = windID;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
