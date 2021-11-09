package com.EnergyForecasting.Model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class APICaller {
    ArrayList<String> windSpeed;
    ArrayList<String> windDirDeg;
    ArrayList<String> windGust;
    ArrayList<String> maxWindSpeed;
    ArrayList<String> minWindSpeed;
    ArrayList<String> solarWM2;
    ArrayList<String> avgDewpointC;

    public APICaller() {
        ArrayList<String> windSpeed= new ArrayList<String>();
        ArrayList<String> windDirDeg= new ArrayList<String>();
        ArrayList<String> windGust= new ArrayList<String>();
        ArrayList<String> maxWindSpeed= new ArrayList<String>();
        ArrayList<String> minWindSpeed= new ArrayList<String>();
        ArrayList<String> solarWM2= new ArrayList<String>();
        ArrayList<String> avgDewpointC= new ArrayList<String>();
    }

    public void getForecastData(boolean hourly, int days, String longitude, String latitude, String longChar, String latChar) throws IOException, InterruptedException {
        String data[];
        if (hourly){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+latitude+"%C2%B0%20"+latChar+",%20"+ longitude +"%C2%B0%20"+longChar+"?plimit="+24*days+"&filter=1hr"))
                    .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                    .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data= response.body().split("\\s|:|,");
        }else{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://aerisweather1.p.rapidapi.com/forecasts/"+latitude+"%C2%B0%20"+latChar+",%20"+ longitude +"%C2%B0%20"+longChar+"?plimit="+1*days))
                    .header("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                    .header("x-rapidapi-key", "f065867663msh0c61f0d4699f6bap11e507jsn2a32eff05892")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data= response.body().split("\\s|:|,");
        }

        System.out.println(Arrays.toString(data));


        for (int i=0; i<data.length;i++) {
            if(data[i].contains("windSpeedKPH")){
                windSpeed.add(data[i+1]);
            }
            if(data[i].contains("windDirDeg")){
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
            if(data[i].contains("avgDewpointC")){
                avgDewpointC.add(data[i+1]);
            }
        }
    }


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
    public ArrayList<String> getAvgDewpointC() {
        return avgDewpointC;
    }
}

