package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Sprites implements Serializable {
    private static final  long serialVersionUID = 1L;

    @JsonProperty("front_default")
    private String front_default;

    public Sprites() {
    }

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    @Override
    public String toString() {
        return "Sprites{" +
                "front_default='" + front_default + '\'' +
                '}';
    }
}
