package edu.badpals.pokeapi.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Animated implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    // URL de la imagen frontal animada por defecto del Pokémon.
    @JsonProperty("front_default")
    private String front_default;

    public Animated(){}

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    @Override
    public String toString() {
        return "Animated{" +
                "front_default='" + front_default + '\'' +
                '}';
    }
}
