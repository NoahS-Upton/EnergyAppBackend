package com.EnergyForecasting.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "forecast")
public class Forecast implements Serializable {
    //input variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name="id")
    private Long id;
    @Column(name="hourly")
    private boolean hourly;
    @Column(name="days")
    private int days;
    @Column(name="onshore")
    private boolean onshore;
    @Column(name="offshore")
    private boolean offshore;
    @Column(name="solar")
    private boolean solar;
    @Column(name="userID")
    private String userID;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "forecastRegions",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "regionid")
    )
    private Set<Region> forecastRegions= new HashSet<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "forecastCounties",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "countyid")
    )
    private Set<County> forecastCounties=new HashSet<>();



    // Outputs to screen
    @JsonIgnore
    @OneToMany(mappedBy = "forecast")
    private Set<ForecastOutput> countyOutputs= new HashSet<>();



    public Forecast() {
    }

    @Autowired
    public Forecast(boolean hourly, int days, Set<Region> region, Set<County> county, boolean onshore, boolean offshore, boolean solar, String userID) {
        this.hourly = hourly;
        this.days = days;
        this.forecastRegions = region;
        this.forecastCounties = county;
        this.onshore = onshore;
        this.offshore = offshore;
        this.solar = solar;
        this.userID=userID;
    }

    //getters and setters for retrieving forecast(input variables)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setForecastId(Long id) {
        this.id = id;
    }

    public boolean isHourly() {
        return hourly;
    }
    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public boolean isOnshore() {
        return onshore;
    }
    public void setOnshore(boolean onshore) {
        this.onshore = onshore;
    }
    public boolean isOffshore() {
        return offshore;
    }
    public void setOffshore(boolean offshore) {
        this.offshore = offshore;
    }
    public boolean isSolar() {
        return solar;
    }
    public void setSolar(boolean solar) {
        this.solar = solar;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Set<Region> getForecastRegions() {
        return forecastRegions;
    }

    public void setForecastRegions(Set<Region> forecastRegions) {
        this.forecastRegions = forecastRegions;
    }

    public Set<County> getForecastCounties() {
        return forecastCounties;
    }

    public void setForecastCounties(Set<County> forecastCounties) {
        this.forecastCounties = forecastCounties;
    }

    //getters and setters for forecast output variables


//    public Set<ForecastCountyOutputs> getCountyOutputs() {
//        return countyOutputs;
//    }
//
//    public void setCountyOutputs(Set<ForecastCountyOutputs> countyOutputs) {
//        this.countyOutputs = countyOutputs;
//    }


    public Set<ForecastOutput> getCountyOutputs() {
        return countyOutputs;
    }

    public void setCountyOutputs(Set<ForecastOutput> countyOutputs) {
        this.countyOutputs = countyOutputs;
    }

    public void assignCounty(County county) {
        forecastCounties.add(county);
    }
    public void assignRegion(Region region) {
        forecastRegions.add(region);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", hourly=" + hourly +
                ", days=" + days +
                ", region=" + forecastRegions +
                ", county=" + forecastCounties +
                ", onshore=" + onshore +
                ", offshore=" + offshore +
                ", solar=" + solar +
                ", userID='" + userID + '\'' +
                '}';
    }


}
