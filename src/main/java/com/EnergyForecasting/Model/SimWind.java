package com.EnergyForecasting.Model;

import javax.persistence.*;

@Entity
@Table(name = "simWind")
public class SimWind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "windID")
    private Long windID;
    private Simulation simulation;
    private double value;

    public SimWind(Simulation simulation, double value) {
        this.simulation = simulation;
        this.value = value;
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
