package com.EnergyForecasting.Model;

import java.util.ArrayList;
import java.util.HashMap;

// a class that is instantiated on running or rerunning a simulation and sent to front end
public class SimulationOutput {
    private ArrayList<String> counties;
    private ArrayList<String> regions;
    private int intervals;
    private HashMap<String, ArrayList<Double>> solarOutputs;
    private HashMap<String, ArrayList<Double>> offShoreOutputs;
    private HashMap<String, ArrayList<Double>> onShoreOutputs;

    public SimulationOutput(ArrayList<String> counties, ArrayList<String> regions, int intervals, HashMap<String, ArrayList<Double>> solarOutputs, HashMap<String, ArrayList<Double>> offShoreOutputs, HashMap<String, ArrayList<Double>> onShoreOutputs) {
        this.counties = counties;
        this.regions = regions;
        this.intervals = intervals;
        this.solarOutputs = solarOutputs;
        this.offShoreOutputs = offShoreOutputs;
        this.onShoreOutputs = onShoreOutputs;
    }

    public ArrayList<String> getCounties() {
        return counties;
    }
    public void setCounties(ArrayList<String> counties) {
        this.counties = counties;
    }
    public ArrayList<String> getRegions() {
        return regions;
    }
    public void setRegions(ArrayList<String> regions) {
        this.regions = regions;
    }
    public int getIntervals() {
        return intervals;
    }
    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }
    public HashMap<String, ArrayList<Double>> getSolarOutputs() {
        return solarOutputs;
    }
    public void setSolarOutputs(HashMap<String, ArrayList<Double>> solarOutputs) {
        this.solarOutputs = solarOutputs;
    }
    public HashMap<String, ArrayList<Double>> getOffShoreOutputs() {
        return offShoreOutputs;
    }
    public void setOffShoreOutputs(HashMap<String, ArrayList<Double>> offShoreOutputs) {
        this.offShoreOutputs = offShoreOutputs;
    }
    public HashMap<String, ArrayList<Double>> getOnShoreOutputs() {
        return onShoreOutputs;
    }
    public void setOnShoreOutputs(HashMap<String, ArrayList<Double>> onShoreOutputs) {
        this.onShoreOutputs = onShoreOutputs;
    }
}
