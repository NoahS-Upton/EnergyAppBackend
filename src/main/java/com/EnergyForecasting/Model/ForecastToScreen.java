package com.EnergyForecasting.Model;

import java.util.ArrayList;
import java.util.HashMap;

//used to package up forecast results to be passed to front end
public class ForecastToScreen {
   private ArrayList<String> counties;
   private ArrayList<String> regions;
   private int intervals;
   private HashMap<String,ArrayList<ForecastOutput>> fts;
   private Forecast forecast;

   //constructor
    public ForecastToScreen(ArrayList<String> counties, ArrayList<String> regions, int intervals, HashMap<String, ArrayList<ForecastOutput>> fts, Forecast forecast) {
        this.counties = counties;
        this.regions = regions;
        this.intervals = intervals;
        this.fts = fts;
        this.forecast = forecast;
    }

    public ForecastToScreen() {
    }

    //getters and setters
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
    public HashMap<String, ArrayList<ForecastOutput>> getFts() {
        return fts;
    }
    public void setFts(HashMap<String, ArrayList<ForecastOutput>> fts) {
        this.fts = fts;
    }
    public Forecast getForecast() {
        return forecast;
    }
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
