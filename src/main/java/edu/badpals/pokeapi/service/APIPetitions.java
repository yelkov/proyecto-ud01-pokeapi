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

/**
 * Clase responsable de realizar peticiones a la API de Pokémon.
 *
 * Proporciona métodos para obtener información detallada sobre un Pokémon, incluyendo
 * sus datos básicos, áreas de encuentro y la imagen correspondiente. Los datos se
 * estructuran en un objeto {@link PokemonData} que puede ser utilizado en la aplicación.
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 * @use Utiliza el método {@link #getPokemonData(String)} para obtener la información
 * completa de un Pokémon a partir de su nombre.
 */
public class APIPetitions {
    // URL base para obtener información sobre Pokémon
    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon-species/";
    // URL dividida en 2 partes para obtener información sobre las áreas donde se encuentra el Pokémon en el juego
    private static final String ENCOUNTERS_URL_BEGIN = "https://pokeapi.co/api/v2/pokemon/";
    private static final String ENCOUNTERS_URL_END = "/encounters";
    // URL para obtener la imagen del Pokémon
    private static final String IMAGE_URL = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Obtiene, a partir de varias llamadas a otros métodos, la información necesaria para construir
     * un objeto PokemonData, que será el objeto que se manipule en memoria y se muestre al usuario.
     *
     * @param pokemonName El nombre del Pokémon a buscar.
     * @return Un objeto PokemonData que contiene la información del Pokémon.
     * @throws IOException Si hay un error al realizar las peticiones a la API.
     */
    public static PokemonData getPokemonData(String pokemonName) throws IOException {
        Pokemon pokemon = askAPIforPokemon(pokemonName);
        List<Area> areas = askAPIforArea(pokemon.getId());
        PokemonImage image = askAPIforImage(pokemonName);
        PokemonData pokemonData = new PokemonData(pokemon,areas,image);

        return pokemonData;
    }

    /**
     * Realiza una petición a la API para obtener los datos básicos de un Pokémon.
     *
     * @param pokemonName El nombre del Pokémon a buscar.
     * @return Un objeto Pokemon que representa al Pokémon solicitado.
     * @throws IOException Si hay un error al realizar la petición.
     */
    private static Pokemon askAPIforPokemon(String pokemonName) throws IOException{
        try{
            URL jsonURL = new URL(POKEMON_URL + pokemonName);
            ObjectMapper mapper = new ObjectMapper();
            // Configurar el mapper para ignorar propiedades que no deseamos manejar
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            Pokemon pokemon = mapper.readValue(jsonURL, Pokemon.class);

            return pokemon;

        } catch (MalformedURLException e) {
            // Si hay errores en la formación de la URL, registrar un error
            ErrorLogger.saveErrorLog("The URL of pokemon is invalid");
        }
        return null;
    }

    /**
     * Realiza una petición a la API para obtener las áreas en las que se encuentra un Pokémon en el juego.
     *
     * @param pokemonID El ID del Pokémon para el cual se desean obtener las áreas de encuentro.
     * @return Una lista de objetos Area representando las áreas de encuentro.
     */
    private static List<Area> askAPIforArea(int pokemonID){
        try{
            URL jsonURL = new URL(ENCOUNTERS_URL_BEGIN + pokemonID + ENCOUNTERS_URL_END);
            ObjectMapper mapper = new ObjectMapper();
            // Configurar el mapper para ignorar propiedades que no deseamos manejar
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            List<Area> areas = mapper.readValue(jsonURL, new TypeReference<List<Area>>(){});

            return areas;

        } catch (MalformedURLException e) {
            // Si hay errores en la formación de la URL, registrar un error
            ErrorLogger.saveErrorLog("The URL of area is invalid");
        }catch (IOException e) {
            // Si hay errores deserializando las áreas, registrar un error
            ErrorLogger.saveErrorLog("Error reading areas from jsonURL.");
        }
        return null;
    }

    /**
     * Realiza una petición a la API para obtener la imagen de un Pokémon específico.
     *
     * @param pokemonName El nombre del Pokémon para el cual se desea obtener la imagen.
     * @return Un objeto PokemonImage que representa la imagen del Pokémon.
     * @throws IOException Si hay un error al realizar la petición.
     */
    private static PokemonImage askAPIforImage(String pokemonName) throws IOException {
        try{
            URL jsonURL = new URL(IMAGE_URL + pokemonName);
            ObjectMapper mapper = new ObjectMapper();
            // Configurar el mapper para ignorar propiedades que no deseamos manejar
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(jsonURL, PokemonImage.class);
        }catch (MalformedURLException e) {
            // Si hay errores durante la petición, registrar un error
            ErrorLogger.saveErrorLog("The URL of image is invalid");
        }
        return null;
    }
}
