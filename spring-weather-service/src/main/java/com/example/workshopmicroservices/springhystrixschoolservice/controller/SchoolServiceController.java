package com.example.workshopmicroservices.springhystrixschoolservice.controller;

import com.example.workshopmicroservices.springhystrixschoolservice.model.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api
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

    //http response for getStudent operation : 200 / 401 / 403 / 404 (use ApiResponses annotation)
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "action non autorisé"),
            @ApiResponse(code = 403, message = "accès refusé"),
            @ApiResponse(code = 404, message = "server not found"),
    })

    // recherche de la météo par nom de ville
    @RequestMapping(value = "/getCityByName/{cityname}", method = RequestMethod.GET)
    @ApiOperation(value = "recupération de tous les étudiants",response = String.class)
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
    @ApiOperation(value = "recupération de tous les étudiants",response = String.class)
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
    @ApiOperation(value = "recupération de tous les étudiants",response = String.class)
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
