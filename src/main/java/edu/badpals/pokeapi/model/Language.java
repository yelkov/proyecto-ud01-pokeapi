package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language {
    @JsonProperty("name")
    private String name;

    public Language(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                '}';
    }
}