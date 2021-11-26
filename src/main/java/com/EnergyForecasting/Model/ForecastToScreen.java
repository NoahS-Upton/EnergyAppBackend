package com.EnergyForecasting.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class ForecastToScreen {
    private String county;
    private HashMap<Integer, ArrayList<Double>> solarOutputs;
    private HashMap<Integer, ArrayList<Double>> offShoreOutputs;
    private HashMap<Integer, ArrayList<Double>> onShoreOutputs;

    public ForecastToScreen(String county, HashMap<Integer, ArrayList<Double>> solarOutputs, HashMap<Integer, ArrayList<Double>> offShoreOutputs, HashMap<Integer, ArrayList<Double>> onShoreOutputs) {
        this.county = county;
        this.solarOutputs = solarOutputs;
        this.offShoreOutputs = offShoreOutputs;
        this.onShoreOutputs = onShoreOutputs;
    }
}
