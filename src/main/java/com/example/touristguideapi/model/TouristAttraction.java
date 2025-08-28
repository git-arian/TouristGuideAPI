package com.example.touristguideapi.model;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;

    public TouristAttraction() {} // <- til JSON deserialisering
    // Skal have en tom konstruktor så Spring kan lave objektet ud fra JSON.

    public TouristAttraction(String name, String description, String city){
        this.description = description;
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
