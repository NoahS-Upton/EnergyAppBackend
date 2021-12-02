package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import javax.persistence.*;
@Entity
@Table(name = "forecast_outputs")
public class ForecastOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long outputID;
    private int interval;
    private String county;
    private Double offshoreOutput;
    private Double onshoreOutput;
    private Double solarOutput;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Forecast forecast;

    public ForecastOutput(@NonNull int interval,@NonNull String county,@NonNull Double offshoreOutput, @NonNull Double onshoreOutput,@NonNull Double solarOutput,@NonNull Forecast forecast) {
        this.interval = interval;
        this.county = county;
        this.offshoreOutput = offshoreOutput;
        this.onshoreOutput = onshoreOutput;
        this.solarOutput = solarOutput;
        this.forecast = forecast;
    }

    public ForecastOutput() {
    }

    public void assignForecast(Forecast forecast) {
        this.forecast=forecast;
    }

    public Long getOutputID() {
        return outputID;
    }

    public void setOutputID(Long outputID) {
        this.outputID = outputID;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Double getOffshoreOutput() {
        return offshoreOutput;
    }

    public void setOffshoreOutput(Double offshoreOutput) {
        this.offshoreOutput = offshoreOutput;
    }

    public Double getOnshoreOutput() {
        return onshoreOutput;
    }

    public void setOnshoreOutput(Double onshoreOutput) {
        this.onshoreOutput = onshoreOutput;
    }

    public Double getSolarOutput() {
        return solarOutput;
    }

    public void setSolarOutput(Double solarOutput) {
        this.solarOutput = solarOutput;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
