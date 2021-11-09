package com.EnergyForecasting.Model;

public class Calculation {

    public Calculation() {
    }

    public static double solarOutput(double capacity, double MW2){
            double output = capacity*MW2*0.75;
            return output;
        }

        public static double windOutput(double capacity, double windspeed){
            double output=capacity*windspeed;
            return output;
        }
}



