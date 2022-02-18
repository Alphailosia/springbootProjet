package com.example.workshopmicroservices.springhystrixschoolservice.controller;

import com.example.workshopmicroservices.springhystrixschoolservice.model.City;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SchoolServiceController {

    // initialisation de la liste de ville
    private Map<String, ArrayList<City>> cityBD = new HashMap<String, ArrayList<City>>(){
        {
            put("France", new ArrayList<City>(){
                {
                    add(new City("Paris","75000","Temps Pluvieux, Température 20°"));
                    add(new City("Marseille","13000","Temps Ensoleillé, température 16°"));
                }
            });
            put("Japon", new ArrayList<City>(){
                {
                    add(new City("Tokyo","75001","Temps Pluvieux, Température 20°"));
                    add(new City("Kyoto","13001","Temps Ensoleillé, température 16°"));
                }
            });
        }
    };

    // recherche de la météo par nom de ville
    @RequestMapping(value = "/getCityByName/{cityname}", method = RequestMethod.GET)
    public String getCityByName(@PathVariable String cityname){
        for(String country : cityBD.keySet()){
            for(City city : cityBD.get(country)){
                if(city.getName().equals(cityname)){
                    return "Météo pour la ville "+cityname+" : "+city.getWeather();
                }
            }
        }
        return "No city found";
    }

    // recherche de la météo par zipcode de la ville
    @RequestMapping(value = "/getCityByZipcode/{cityzipcode}", method = RequestMethod.GET)
    public String getCityByZipcode(@PathVariable String cityzipcode){
        for(String country : cityBD.keySet()){
            for(City city : cityBD.get(country)){
                if(city.getZipcode().equals(cityzipcode)){
                    return "Météo pour la ville "+cityzipcode+" : "+city.getWeather();
                }
            }
        }
        return "No city Found";
    }

    // recherche de la météo par nom du pays
    @RequestMapping(value = "/getCityByCountry/{country}", method = RequestMethod.GET)
    public String getCityByCountry(@PathVariable String country){
        ArrayList<City> l = new ArrayList<City>();
        for(String pays : cityBD.keySet()){
                if(pays.equals(country)){
                    l = cityBD.get(pays);
                }
        }
        if(l.isEmpty()){
            return "No country Found";
        }
        else{
            String s = "Météo dans le pays "+country+" : \n";
            for(City c : l){
                s+= c.getName()+", météo : "+c.getWeather()+"\n";
            }
            return s;
        }
    }
}
