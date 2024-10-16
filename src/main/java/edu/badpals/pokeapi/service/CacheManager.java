package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;

import java.io.File;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {
    private static final String DIR_CACHE = "cache/";

    public static void saveCache(Pokemon pokemon, List<Area> areas){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DIR_CACHE + pokemon.getName() + ".json"),pokemon);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DIR_CACHE + pokemon.getName() + "_areas.json"),areas);
        }catch (IOException e){
            System.out.println("Error saving cache.");
            e.printStackTrace();
        }
    }

    public static Map<Pokemon,List<Area>> loadCache(String pokemonName){
        Map<Pokemon,List<Area>> loadedCache = new HashMap<>();
        try{
            ObjectMapper mapper = new ObjectMapper();
            Pokemon pokemon = mapper.readValue(new File(DIR_CACHE + pokemonName + ".json"), Pokemon.class);
            List<Area> areas = mapper.readValue(new File(DIR_CACHE + pokemonName + "_areas.json"), new TypeReference<List<Area>>() {});
            loadedCache.put(pokemon,areas);
            return loadedCache;
        } catch (IOException e) {
            System.out.println("Error loading cache. ");
            e.printStackTrace();
        }
        return loadedCache;
    }

}
