package edu.badpals.pokeapi.model;

import java.io.Serializable;
import java.util.List;

public class PokemonData implements Serializable {
    private static final  long serialVersionUID = 1L;
    private Pokemon pokemon;
    private List<Area> areas;

    public PokemonData() {
    }

    public PokemonData(Pokemon pokemon, List<Area> areas) {
        this.pokemon = pokemon;
        this.areas = areas;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "PokemonData{" +
                "pokemon=" + pokemon +
                ", areas=" + areas +
                '}';
    }
}
