package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.ObjectMethods;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {
    private static final String DIR_CACHE = "cache/";

    public static void saveCache(PokemonData data){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DIR_CACHE + data.getPokemon().getName() + ".json"),data);

            URL imageURL = new URL(data.getPokemonImage().obtainImage());
            try(InputStream in = imageURL.openStream();){
                Files.copy(in, Paths.get(DIR_CACHE + data.getPokemon().getName() + "_image.png"));
            }

        }catch (IOException e){
            System.out.println("Error saving cache.");
            e.printStackTrace();
        }
    }

    public static PokemonData loadCache(String pokemonName) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(DIR_CACHE + pokemonName + ".json"), PokemonData.class);

    }

    public static String loadImageCache(String pokemonName){
        String pokemonImage = "file:" + DIR_CACHE + pokemonName + "_image.png";
        return pokemonImage;
    }

    public static void deleteCache() throws  IOException{
        File cacheDirectory = new File(DIR_CACHE);

        if(cacheDirectory.exists() && cacheDirectory.isDirectory()){

            File[] cacheFiles = cacheDirectory.listFiles();
            if (cacheFiles != null){
                for (File file : cacheFiles){
                    if (file.isFile()){
                        file.delete();
                    }
                }
            }
        }else{
            throw new IOException("Cache directory does not exist: " + DIR_CACHE);
        }
    }
}
