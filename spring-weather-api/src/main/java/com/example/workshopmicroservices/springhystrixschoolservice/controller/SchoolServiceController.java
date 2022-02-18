package com.example.workshopmicroservices.springhystrixschoolservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchoolServiceController {

    @Autowired
    RestTemplate restTemplate;


    // recherche de la météo par nom de ville
    @RequestMapping(value = "/show-weather-byname/{cityname}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getCityByName_Fallback")
    public String getCityByName(@PathVariable String cityname){
        String response = restTemplate
                .exchange("http://localhost:9098/getCityByName/{cityname}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, cityname).getBody();
        return response;
    }

    @SuppressWarnings("unsed")
    public String getCityByName_Fallback(String cityname){
        return "Weather Service not enable";
    }

    // recherche de la météo par zipcode de la ville
    @RequestMapping(value = "/show-weather-byzipcode/{cityzipcode}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getCityByName_Fallback")
    public String getCityByZipcode(@PathVariable String cityzipcode){
        String response = restTemplate
                .exchange("http://localhost:9098/getCityByZipcode/{cityzipcode}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, cityzipcode).getBody();
        return response;
    }

    // recherche de la météo par nom du pays
    @RequestMapping(value = "/show-weather-bycountry/{country}", method = RequestMethod.GET)
    public String getCityByCountry(@PathVariable String country){
        String response = restTemplate
                .exchange("http://localhost:9098/getCityByCountry/{country}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, country).getBody();
        return response;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
