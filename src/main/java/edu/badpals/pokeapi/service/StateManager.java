package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.PokemonData;

import java.io.File;
import java.io.IOException;

public class StateManager {
    private static final String LAST_STATE_PATH =".appData/lastState.json";

    public static void saveState(PokemonData currentState){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(LAST_STATE_PATH),currentState);
        } catch (IOException e) {
            System.out.println("Error saving current state");
            e.printStackTrace();
        }
    }

    public static PokemonData loadLastState() throws IOException{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(LAST_STATE_PATH),PokemonData.class);
    }
}
