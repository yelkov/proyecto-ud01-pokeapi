package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationArea {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LocationArea{" +
                "name='" + name + '\'' +
                '}';
    }
}
