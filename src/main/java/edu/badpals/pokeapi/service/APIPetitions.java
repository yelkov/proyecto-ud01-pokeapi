package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;
import edu.badpals.pokeapi.model.PokemonImage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;

public class APIPetitions {
    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon-species/";
    private static final String ENCOUNTERS_URL_BEGIN = "https://pokeapi.co/api/v2/pokemon/";
    private static final String ENCOUNTERS_URL_END = "/encounters";
    private static final String IMAGE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static PokemonData getPokemonData(String pokemonName) throws IOException {
        Pokemon pokemon = askAPIforPokemon(pokemonName);
        List<Area> areas = askAPIforArea(pokemon.getId());
        PokemonImage image = askAPIforImage(pokemonName);
        PokemonData pokemonData = new PokemonData(pokemon,areas,image);

        return pokemonData;
    }

    private static Pokemon askAPIforPokemon(String pokemonName) throws IOException{
        try{
            URL jsonURL = new URL(POKEMON_URL + pokemonName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            Pokemon pokemon = mapper.readValue(jsonURL, Pokemon.class);

            return pokemon;

        } catch (MalformedURLException e) {
            System.out.println("The URL of Pokemon is invalid");
            e.printStackTrace();
        }
        return null;
    }

    private static List<Area> askAPIforArea(int pokemonID){
        try{
            URL jsonURL = new URL(ENCOUNTERS_URL_BEGIN + pokemonID + ENCOUNTERS_URL_END);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            List<Area> areas = mapper.readValue(jsonURL, new TypeReference<List<Area>>(){});

            return areas;

        } catch (MalformedURLException e) {
            System.out.println("The URL of area is invalid");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("Error reading areas from jsonURL.");
            e.printStackTrace();
        }
        return null;
    }

    private static PokemonImage askAPIforImage(String pokemonName) throws IOException {
        try{
            URL jsonURL = new URL(IMAGE_URL + pokemonName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(jsonURL, PokemonImage.class);
        }catch (MalformedURLException e) {
            System.out.println("The URL of image is invalid");
        }
        return null;
    }
}
