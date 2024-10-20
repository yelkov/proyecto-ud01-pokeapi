package edu.badpals.pokeapi.service;
import edu.badpals.pokeapi.model.PokemonData;

import java.io.*;

public class StateManager {
    // Directorio donde se guardarán los estados guardados
    private static final String LAST_STATE_DIR =".appData/";

    /**
     * Guarda el estado actual de un objeto PokemonData en un archivo binario.
     * El archivo se guarda en el directorio especificado por LAST_STATE_DIR,
     * y su nombre se basa en el nombre del usuario que está logueado en ese momento.
     *
     * @param currentState El objeto PokemonData que está en memoria y se quiere guardar.
     * @param username     El nombre de usuario que será usado para nombrar el archivo.
     */
    public static void saveState(PokemonData currentState, String username){
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(LAST_STATE_DIR + "lastState_" + username + ".bin"))){
            // Escribe el objeto PokemonData en el archivo
            writer.writeObject(currentState);
        } catch (IOException e) {
            // En caso de error al guardar, registra un log en el archivo error.log de .appData
            ErrorLogger.saveErrorLog("Error saving current state");
        }
    }

    /**
     * Carga el último estado guardado de un archivo binario para el usuario especificado.
     * Si no se encuentra o hay un error, se devolverá null.
     *
     * @param username El nombre de usuario que se loguea para cargar su último estado.
     * @return         El objeto PokemonData recuperado del archivo, o null si hay un error.
     */
    public static PokemonData loadLastState(String username) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(LAST_STATE_DIR + "lastState_" + username + ".bin"))) {
            while (true) {
                try {
                    Object o = reader.readObject();
                    if (o instanceof PokemonData) {
                        return (PokemonData) o;
                    }
                } catch (EOFException e) {
                    // Si se llega al final del archivo, se lanza la excepción y rompe el ciclo
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // En caso de error al cargar, registra un mensaje de error
            ErrorLogger.saveErrorLog("Error loading last state");
        }
        return null;
    }
}
