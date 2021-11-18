package com.EnergyForecasting.Model;

import java.util.ArrayList;

public class CountyOutputs {
    private int forecastID;
    private CountyList countyList;
    private ArrayList<Double> offshoreOutput;
    private ArrayList<Double> onshoreOutput;
    private ArrayList<Double> solarOutput;

    public CountyOutputs(int forecastID, CountyList countyList, ArrayList<Double> offshoreOutput, ArrayList<Double> onshoreOutput, ArrayList<Double> solarOutput) {
        this.forecastID = forecastID;
        this.countyList=countyList;
        this.offshoreOutput = offshoreOutput;
        this.onshoreOutput = onshoreOutput;
        this.solarOutput = solarOutput;
    }

    public int getForecastID() {
        return forecastID;
    }

    public void setForecastID(int forecastID) {
        this.forecastID = forecastID;
    }

    public ArrayList<Double> getOffshoreOutput() {
        return offshoreOutput;
    }

    public void setOffshoreOutput(ArrayList<Double> offshoreOutput) {
        this.offshoreOutput = offshoreOutput;
    }

    public ArrayList<Double> getOnshoreOutput() {
        return onshoreOutput;
    }

    public void setOnshoreOutput(ArrayList<Double> onshoreOutput) {
        this.onshoreOutput = onshoreOutput;
    }

    public ArrayList<Double> getSolarOutput() {
        return solarOutput;
    }

    public void setSolarOutput(ArrayList<Double> solarOutput) {
        this.solarOutput = solarOutput;
    }
}
