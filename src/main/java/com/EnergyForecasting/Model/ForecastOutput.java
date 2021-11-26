package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
@Table(name = "forecast_outputs")
public class ForecastOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long outputID;
    private Long interval;
    private String county;
    private Double offshoreOutput;
    private Double onshoreOutput;
    private Double solarOutput;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Forecast forecast;

    public ForecastOutput(Long interval, String county, Double offshoreOutput, Double onshoreOutput, Double solarOutput, Forecast forecast) {
        this.interval = interval;
        this.county = county;
        this.offshoreOutput = offshoreOutput;
        this.onshoreOutput = onshoreOutput;
        this.solarOutput = solarOutput;
        this.forecast = forecast;
    }

    public void assignForecast(Forecast forecast) {
        this.forecast=forecast;
    }
}