package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "SimulationDaylight")
public class SimulationDaylight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long daylightID;
    private double daylightHours;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Simulation simulation;

    public SimulationDaylight(double daylightHours, Simulation simulation) {
        this.daylightHours = daylightHours;
        this.simulation = simulation;
    }
    public void assignSimulation(Simulation simulation) {
        this.simulation=simulation;
    }

    public Long getDaylightID() {
        return daylightID;
    }

    public double getDaylightHours() {
        return daylightHours;
    }

    public void setDaylightHours(double daylightHours) {
        this.daylightHours = daylightHours;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}