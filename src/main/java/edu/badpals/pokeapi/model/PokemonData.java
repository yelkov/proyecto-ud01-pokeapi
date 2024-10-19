package edu.badpals.pokeapi.model;

import java.io.Serializable;
import java.util.List;

public class PokemonData implements Serializable {
    private static final  long serialVersionUID = 1L;
    private Pokemon pokemon;
    private List<Area> areas;
    private PokemonImage pokemonImage;

    public PokemonData() {
    }

    public PokemonData(Pokemon pokemon, List<Area> areas, PokemonImage pokemonData) {
        this.pokemon = pokemon;
        this.areas = areas;
        this.pokemonImage = pokemonData;
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

    public PokemonImage getPokemonImage() {
        return pokemonImage;
    }

    public void setPokemonImage(PokemonImage pokemonImage) {
        this.pokemonImage = pokemonImage;
    }

    @Override
    public String toString() {
        return "PokemonData{" +
                "pokemonImage=" + pokemonImage +
                ", areas=" + areas +
                ", pokemon=" + pokemon +
                '}';
    }
}
