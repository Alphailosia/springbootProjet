package com.example.workshopmicroservices.springhystrixschoolservice.model;

public class City {

    private String name;
    private String zipcode;
    private String weather;

    public City(String name, String zipcode, String weather) {
        this.name = name;
        this.zipcode = zipcode;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", weather='" + weather + '\'' +
                '}';
    }
}
