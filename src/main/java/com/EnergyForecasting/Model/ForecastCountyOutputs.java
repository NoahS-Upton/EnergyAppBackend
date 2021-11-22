package com.EnergyForecasting.Model;

import javax.persistence.*;
@Entity
@Table(name = "forecast_county_outputs")
public class ForecastCountyOutputs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long ID;
    private double windOutput;
    private double solarOutput;

    private Forecast forecast;




}
