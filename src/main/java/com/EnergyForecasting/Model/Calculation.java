package com.EnergyForecasting.Model;

public class Calculation {

    public Calculation() {
    }

    public static double solarHourlyOutput(double capacity, double hours){
            double output = capacity*hours*0.75;
            return output;
        }

    public static double solarRadOutput(double capacity, double rads){
        double output = capacity*rads*0.75;
        return output;
    }


        public static double windOutput(double capacity, double windspeed){
            double output;
            if (windspeed>50 && windspeed<=90){
                output=capacity;
            }else if(windspeed>18 && windspeed<=24){
                output=capacity* 0.1;
            }else if(windspeed>24 && windspeed<=28){
                output=capacity* 0.2;
            }else if(windspeed>28 && windspeed<=32){
                output=capacity* 0.4;
            }else if(windspeed>32 && windspeed<=36){
                output=capacity* 0.5;
            }else if(windspeed>36 && windspeed<=40){
                output=capacity* 0.7;
            }else if(windspeed>40 && windspeed<=45){
                output=capacity* 0.8;
            }else if(windspeed>45 && windspeed<=50){
                output=capacity* 0.9;
            }else{
                output = 0.0;
            }

            return output;
        }
}



