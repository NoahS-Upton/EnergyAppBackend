package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.PlantNotFoundException;
import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Repository.PlantRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/*
    service for retrieving and updating plants in database
 */
@Service
@Transactional
public class PlantService {
    private final PlantRepo plantRepo;

    //constructor
    @Autowired
    public PlantService(PlantRepo plantRepo) {
        this.plantRepo = plantRepo;
    }
    //adds new plant
    public Plant addPlant(Plant plant){
        return plantRepo.save(plant);
    }
    //adds new plant by specific values
    public Plant addPlant(String name, Double capacity, String type, String region, String county){
        Plant p= new Plant(name,capacity,type,region,county);
        return plantRepo.save(p);
    }
    //gets all plants
    public List<Plant> getAllPlants() {
        List<Plant> allPlants = null;
        try {
            allPlants=plantRepo.findAll();

        } catch (NullPointerException e) {
            throw e;
        }
        return allPlants;
    }
    //gets all plants in a region
    public ArrayList<Plant> getPlantsByRegion(@NonNull String reg){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getRegion().equals(reg)){
                temp.add(p);
            }
        }
        return temp;
    }

    //gets all plants in a county
    public ArrayList<Plant> getPlantsByCounty(@NonNull String county){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getCounty().equals(county)){
                temp.add(p);
            }
        }
        return temp;
    }
    //gets plants by offshore/onshore/solar
    public ArrayList<Plant> getPlantsByType(String type){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getType().equals(type)){
                temp.add(p);
            }
        }
        return temp;
    }

    //update an existing plant
    public Plant updatePlant(Plant plant){
        return plantRepo.save(plant);
    }
    //delete an existing plant
    public void deletePlant(Long id){
        plantRepo.deletePlantById(id);
    }
    //gets plant by its id
    public Plant findPlantById(Long id){
        return plantRepo.findPlantById(id).orElseThrow(() -> new PlantNotFoundException("User with ID="+ id+" not found"));
    }

}
