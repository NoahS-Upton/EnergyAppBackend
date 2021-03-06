package com.EnergyForecasting.Model;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;


//class for calling api and passing data to be stored for display to screen or use in further calculations
public class APICaller {
    ArrayList<String> windSpeed;
    ArrayList<String> windDirDeg;
    ArrayList<String> windGust;
    ArrayList<String> maxWindSpeed;
    ArrayList<String> minWindSpeed;
    ArrayList<String> solarWM2;
    ArrayList<String> temperature;

    //constructor
    @Autowired
    public APICaller() { }
    //gets forecast data for a specific longitude and latitude for use in calculations
    public void getForecastData(@NonNull boolean hourly,
                                @NonNull int days,
                                @NonNull String longitude,
                                @NonNull String latitude,
                                @NonNull String longChar,
                                @NonNull String latChar) throws IOException, InterruptedException {
        String data[];
        //calls api dependent on criteria, such as the number of days and if the forecast is hourly
        if (hourly){
            String temp= ""+days*24;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+latitude+",%20"+ longitude +"?plimit="+temp+"&filter=1hr"))
                    .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                    .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data= response.body().split("\\s|:|,");
        }else{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+latitude+",%20"+ longitude +"?plimit="+days))
                    .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                    .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data= response.body().split("\\s|:|,");
        }
        //retrieves important information from data retrieved from api
        getValuesFromData(data);
    }

    //gets forecast data for a specific longitude and latitude location
    public void getForecastDataByLatLong(@NonNull String latLong) throws IOException, InterruptedException {
       //parses longitude and latitude to pass to call
        System.out.println(latLong);
        String latSplit[]= latLong.split(",");
        String latitude= ""+latSplit[0].strip();
        String longitude= ""+latSplit[1].strip();
        String data[];

        //calls api with input long/lat
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+latitude+","+longitude +"?plimit=24&filter=1hr"))
                    .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                    .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data= response.body().split("\\s|:|,");
        //retrieves important information from data retrieved from api
        getValuesFromData(data);
    }

    public void getForecastDataByCity(String cityCountry) throws IOException, InterruptedException {
        //parses city and country from input
        String citySplit[]= cityCountry.split(",");
        String city= ""+citySplit[0].strip();
        String country= ""+citySplit[1].strip();
        String data[];
        //calls api with input city/country
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+city+",%20"+ country +"?plimit=24&filter=1hr"))
                .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        data= response.body().split("\\s|:|,");
        //retrieves important information from data retrieved from api
        getValuesFromData(data);
    }

    //from an input of data from the api, this function pulls out all important information and saves it to class variables
    public void getValuesFromData(@NonNull String[] data){
        ArrayList<String> windSpeed= new ArrayList<String>();
        ArrayList<String> windDirDeg= new ArrayList<String>();
        ArrayList<String> windGust= new ArrayList<String>();
        ArrayList<String> maxWindSpeed= new ArrayList<String>();
        ArrayList<String> minWindSpeed= new ArrayList<String>();
        ArrayList<String> solarWM2= new ArrayList<String>();
        ArrayList<String> temperature= new ArrayList<String>();
        System.out.println(Arrays.toString(data));
        for (int i=0; i<data.length;i++) {
            if(data[i].contains("windSpeedKPH")){
                windSpeed.add(data[i+1]);
            }
            if(data[i].contains("windDirDEG")){
                windDirDeg.add(data[i+1]);
            }
            if(data[i].contains("windGustKPH")){
                windGust.add(data[i+1]);
            }
            if(data[i].contains("windSpeedMaxKPH")){
                maxWindSpeed.add(data[i+1]);
            }
            if(data[i].contains("windSpeedMinKPH")){
                minWindSpeed.add(data[i+1]);
            }
            if(data[i].contains("solradWM2")){
                solarWM2.add(data[i+1]);
            }
            if(data[i].contains("tempC")){
                temperature.add(data[i+1]);
            }
            this.windSpeed=windSpeed;
            this.maxWindSpeed=maxWindSpeed;
            this.minWindSpeed=minWindSpeed;
            this.windDirDeg=windDirDeg;
            this.windGust=windGust;
            this.solarWM2=solarWM2;
            this.temperature= temperature;
        }
    }


    //getters
    public ArrayList<String> getWindSpeed() {
        return windSpeed;
    }
    public ArrayList<String> getWindDirDeg() {
        return windDirDeg;
    }
    public ArrayList<String> getWindGust() {
        return windGust;
    }
    public ArrayList<String> getMaxWindSpeed() {
        return maxWindSpeed;
    }
    public ArrayList<String> getMinWindSpeed() {
        return minWindSpeed;
    }
    public ArrayList<String> getSolarWM2() {
        return solarWM2;
    }
    public ArrayList<String> getTemperature() {
        return temperature;
    }
}

