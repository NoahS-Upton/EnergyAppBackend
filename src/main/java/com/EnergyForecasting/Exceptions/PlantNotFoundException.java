package com.EnergyForecasting.Exceptions;

public class PlantNotFoundException extends RuntimeException {
    public PlantNotFoundException(String message) {
        super(message);
    }
}
