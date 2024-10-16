package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Area {
    @JsonProperty("location_area")
    private LocationArea loc;

    public LocationArea getLocationArea() {
        return loc;
    }

    public void setLocationArea(LocationArea loc) {
        this.loc = loc;
    }

    public String getName(){
        return getLocationArea().getName();
    }

    @Override
    public String toString() {
        return "Area{" +
                "locationArea='" + loc + '\'' +
                '}';
    }
}