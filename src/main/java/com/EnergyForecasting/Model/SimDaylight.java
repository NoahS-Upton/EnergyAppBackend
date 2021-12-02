package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "simDaylight")
public class SimDaylight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "daylightID")
    private Long daylightID;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable( name = "simulationDaylightValues",
            joinColumns = @JoinColumn(name = "daylightID"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Simulation simulation;
    private double value;

    public SimDaylight(Simulation simulation, double value) {
        this.simulation = simulation;
        this.value = value;
    }

    public SimDaylight() {
    }

    public void assignSimulation(Simulation simulation) {
        this.simulation=simulation;
    }


    public Long getDaylightID() {
        return daylightID;
    }

    public void setDaylightID(Long daylightID) {
        this.daylightID = daylightID;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
