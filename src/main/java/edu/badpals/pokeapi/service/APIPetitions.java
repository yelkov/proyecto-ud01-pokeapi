package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;

import java.io.IOException;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;

public class APIPetitions {
    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon-species/";
    private static final String ENCOUNTERS_URL_BEGIN = "https://pokeapi.co/api/v2/pokemon/";
    private static final String ENCOUNTERS_URL_END = "/encounters";

    public static Pokemon askAPIforPokemon(String pokemonName){
        try{
            URL jsonURL = new URL(POKEMON_URL + pokemonName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            Pokemon pokemon = mapper.readValue(jsonURL, Pokemon.class);

            return pokemon;


        } catch (MalformedURLException e) {
            System.out.println("The URL of Pokemon is invalid");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("Error reading pokemon from jsonURL.");
            e.printStackTrace();
        }
        return null;
    }

    public static List<Area> askAPIforArea(String pokemonID){
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
}
