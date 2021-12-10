package com.EnergyForecasting.Exceptions;

//exception for when user is not located
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
