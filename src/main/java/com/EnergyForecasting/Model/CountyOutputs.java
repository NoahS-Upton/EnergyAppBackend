package com.EnergyForecasting.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name = "forecast_county_outputs")
public class CountyOutputs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int forecastID;
    private String county;
    private ArrayList<Double> offshoreOutput;
    private ArrayList<Double> onshoreOutput;
    private ArrayList<Double> solarOutput;
    @Autowired
    public CountyOutputs(int forecastID, String county, ArrayList<Double> offshoreOutput, ArrayList<Double> onshoreOutput, ArrayList<Double> solarOutput) {
        this.forecastID = forecastID;
        this.county=county;
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
