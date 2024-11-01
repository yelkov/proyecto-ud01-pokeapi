package edu.badpals.pokeapi.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Versions implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    @JsonProperty("generation-v")
    private Generation_v generation_v;

    public Versions(){}

    public Generation_v getGeneration_v() {
        return generation_v;
    }

    public void setGeneration_v(Generation_v generation_v) {
        this.generation_v = generation_v;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "generation_v=" + generation_v +
                '}';
    }
}
