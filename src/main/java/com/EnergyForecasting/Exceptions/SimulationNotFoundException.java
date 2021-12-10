package com.EnergyForecasting.Exceptions;

//exception for when simulation is not located
public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException(String s) {
        super(s);
    }
}
