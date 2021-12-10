package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;
import javax.persistence.*;


//used to save values created when forecast is run
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
    //links to associated forecast
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Forecast forecast;

    //constructor
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

    //assigns forecast to output
    public void assignForecast(Forecast forecast) {
        this.forecast=forecast;
    }

    //getters and setters
    public Long getOutputID() {
        return outputID;
    }
    public int getInterval() {
        return interval;
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
    public Double getOnshoreOutput() {
        return onshoreOutput;
    }
    public Double getSolarOutput() {
        return solarOutput;
    }
    public Forecast getForecast() {
        return forecast;
    }
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
