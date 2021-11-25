package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
@Table(name = "forecast_county_outputs")
public class ForecastCountyOutputs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long ID;
    private String county;
    private Double offshoreOutput;
    private Double onshoreOutput;
    private Double solarOutput;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id", referencedColumnName = "id")
    private Forecast forecast;

}
