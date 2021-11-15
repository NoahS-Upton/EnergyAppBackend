package com.EnergyForecasting.Service;

import com.EnergyForecasting.Exceptions.PlantNotFoundException;
import com.EnergyForecasting.Model.Plant;
import com.EnergyForecasting.Repository.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlantService {
    private final PlantRepo plantRepo;

    @Autowired
    public PlantService(PlantRepo plantRepo) {
        this.plantRepo = plantRepo;
    }

    public Plant addPlant(Plant plant){
        return plantRepo.save(plant);
    }

    public Plant addPlant(String name, double capacity, String type, String region, String county){
        Plant p= new Plant(name,capacity,type,region,county);
        return plantRepo.save(p);
    }

    public List<Plant> getAllPlants(){
        return plantRepo.findAll();
    }
    public ArrayList<Plant> getPlantsByRegion(String reg){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getRegion().equals(reg)){
               temp.add(p);
            }
        }
        return temp;
    }
    public ArrayList<Plant> getPlantsByCounty(String county){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getCounty().equals(county)){
                temp.add(p);
            }
        }
        return temp;
    }
    public ArrayList<Plant> getPlantsByType(String type){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (p.getType().equals(type)){
                temp.add(p);
            }
        }
        return temp;
    }
    public List<Plant> getPlantsByCapacity(double maxCap, double minCap){
        ArrayList<Plant> temp= new ArrayList<Plant>();
        for (Plant p: getAllPlants()) {
            if (maxCap>=p.getCapacity()&& p.getCapacity()>=minCap){
                temp.add(p);
            }
        }
        return temp;
    }


    public Plant updatePlant(Plant plant){
        return plantRepo.save(plant);
    }
    public void deletePlant(Long id){
        plantRepo.deletePlantById(id);
    }

    public Plant findPlantById(Long id){
        return plantRepo.findPlantById(id).orElseThrow(() -> new PlantNotFoundException("User with ID="+ id+" not found"));
    }

}
