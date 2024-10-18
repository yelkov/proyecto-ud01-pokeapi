package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PokemonImage implements Serializable {
    private static final  long serialVersionUID = 1L;

    @JsonProperty("sprites")
    private Sprites sprites;

    public PokemonImage() {
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String obtainImage(){
        return sprites.getFront_default();
    }

    @Override
    public String toString() {
        return "PokemonImage{" +
                "sprites=" + sprites +
                '}';
    }
}
