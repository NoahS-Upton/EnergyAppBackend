package com.EnergyForecasting.Exceptions;

//exception for when plant is not located
public class PlantNotFoundException extends RuntimeException {
    public PlantNotFoundException(String message) {
        super(message);
    }
}
