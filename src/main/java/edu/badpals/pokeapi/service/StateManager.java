package edu.badpals.pokeapi.service;
import edu.badpals.pokeapi.model.PokemonData;

import java.io.*;

public class StateManager {
    private static final String LAST_STATE_DIR =".appData/";

    public static void saveState(PokemonData currentState, String username){
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(LAST_STATE_DIR + "lastState_" + username + ".bin"))){
            writer.writeObject(currentState);
        } catch (IOException e) {
            System.out.println("Error saving current state");
            e.printStackTrace();
        }
    }

    public static PokemonData loadLastState(String username) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(LAST_STATE_DIR + "lastState_" + username + ".bin"))) {
            while (true) {
                try {
                    Object o = reader.readObject();
                    if (o instanceof PokemonData) {
                        return (PokemonData) o;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading last state: " + e.getMessage());
        }
        return null;
    }
}
