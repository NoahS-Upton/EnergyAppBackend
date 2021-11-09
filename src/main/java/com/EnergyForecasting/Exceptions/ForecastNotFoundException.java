package com.EnergyForecasting.Exceptions;

public class ForecastNotFoundException extends RuntimeException {
    public ForecastNotFoundException(String s) {
        super(s);
    }
}
