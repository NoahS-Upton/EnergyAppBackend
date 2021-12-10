package com.EnergyForecasting.Exceptions;
//exception for when forecast is not located
public class ForecastNotFoundException extends RuntimeException {
    public ForecastNotFoundException(String s) {
        super(s);
    }
}
