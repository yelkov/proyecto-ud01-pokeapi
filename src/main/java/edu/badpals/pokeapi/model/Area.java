package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Area implements Serializable {
    @JsonProperty("location_area")
    private LocationArea loc;

    public LocationArea getLocation_area() {
        return loc;
    }

    public void setLocationArea(LocationArea loc) {
        this.loc = loc;
    }

    public String obtainName(){
        return getLocation_area().getName().replace("-"," ");
    }

    @Override
    public String toString() {
        return "Area{" +
                "locationArea='" + loc + '\'' +
                '}';
    }
}
