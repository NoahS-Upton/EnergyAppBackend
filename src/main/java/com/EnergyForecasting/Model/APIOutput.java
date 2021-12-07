package com.EnergyForecasting.Model;

import java.util.ArrayList;

public class APIOutput {
    public ArrayList<String> windSpeed;
    public ArrayList<String> windDirDeg;
    public ArrayList<String> windGust;
    public ArrayList<String> maxWindSpeed;
    public ArrayList<String> minWindSpeed;
    public ArrayList<String> solarWM2;
    public ArrayList<String> temperature;

    public APIOutput(ArrayList<String> windSpeed, ArrayList<String> windDirDeg, ArrayList<String> windGust, ArrayList<String> maxWindSpeed, ArrayList<String> minWindSpeed, ArrayList<String> solarWM2,ArrayList<String> temperature) {
        this.windSpeed = windSpeed;
        this.windDirDeg = windDirDeg;
        this.windGust = windGust;
        this.maxWindSpeed = maxWindSpeed;
        this.minWindSpeed = minWindSpeed;
        this.solarWM2 = solarWM2;
        this.temperature=temperature;
    }

    public ArrayList<String> getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(ArrayList<String> windSpeed) {
        this.windSpeed = windSpeed;
    }

    public ArrayList<String> getWindDirDeg() {
        return windDirDeg;
    }

    public void setWindDirDeg(ArrayList<String> windDirDeg) {
        this.windDirDeg = windDirDeg;
    }

    public ArrayList<String> getWindGust() {
        return windGust;
    }

    public void setWindGust(ArrayList<String> windGust) {
        this.windGust = windGust;
    }

    public ArrayList<String> getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(ArrayList<String> maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public ArrayList<String> getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(ArrayList<String> minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public ArrayList<String> getSolarWM2() {
        return solarWM2;
    }

    public void setSolarWM2(ArrayList<String> solarWM2) {
        this.solarWM2 = solarWM2;
    }

    public ArrayList<String> getTemperature() {
        return temperature;
    }

    public void setTemperature(ArrayList<String> temperature) {
        this.temperature = temperature;
    }
}
