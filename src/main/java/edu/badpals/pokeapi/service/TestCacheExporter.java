package edu.badpals.pokeapi.service;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;

import java.util.List;

public class TestCacheExporter {
    public static void main(String[] args) {
        PokemonData bulbasaur = APIPetitions.getPokemonData("bulbasaur");
        System.out.println(bulbasaur);

        System.out.println("");
        CacheManager.saveCache(bulbasaur);


    }
}
