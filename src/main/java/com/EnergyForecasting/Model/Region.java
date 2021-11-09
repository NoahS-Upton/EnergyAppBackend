package com.EnergyForecasting.Model;

public class Region {
    private CountyOutputs[] counties;

    public Region(CountyOutputs[] counties) {
        this.counties = counties;
    }

    public CountyOutputs[] getCounties() {
        return counties;
    }
}
