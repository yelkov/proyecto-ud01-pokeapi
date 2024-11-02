package edu.badpals.pokeapi.model.languages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Version implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    public Version(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Version{" +
                "name='" + name + '\'' +
                '}';
    }
}
