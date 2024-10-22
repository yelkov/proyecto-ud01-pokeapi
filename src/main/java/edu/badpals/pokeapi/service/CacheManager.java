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
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de gestionar la caché de datos de Pokémon.
 *
 * Proporciona métodos para guardar, cargar y eliminar datos de Pokémon y sus imágenes
 * en un directorio de caché local. Los datos se almacenan en formato JSON y las imágenes
 * se guardan como archivos PNG.
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 * @use Utiliza los métodos saveCache, loadCache, loadImageCache y deleteCache para gestionar
 * los datos en caché de los Pokémon.
 */
public class CacheManager {
    // Directorio donde se almacenarán los archivos de caché
    private static final String DIR_CACHE = "cache/";

    /**
     * Guarda en caché los datos de un Pokémon y su imagen.
     * Los datos del Pokémon se guardan en formato JSON y la imagen se descarga y guarda localmente como un archivo PNG.
     *
     * @param data Los datos del Pokémon en memoria que se desea almacenar en caché.
     */
    public static void saveCache(PokemonData data){
        try{
            ObjectMapper mapper = new ObjectMapper();
            // Guardar los datos del Pokémon en un archivo JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DIR_CACHE + data.getPokemon().getName() + ".json"),data);

            // Descargar la imagen del Pokémon y guardarla en un archivo PNG
            URL imageURL = new URL(data.getPokemonImage().obtainImage());
            try(InputStream in = imageURL.openStream();){
                Files.copy(in, Paths.get(DIR_CACHE + data.getPokemon().getName() + "_image.png"), StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (IOException e){
            // Registra un error si falla al guardar en caché
            ErrorLogger.saveErrorLog("Error saving cache. Cache directory does not exist:" + DIR_CACHE);
        }
    }

    /**
     * Carga los datos desde caché de un Pokémon almacenado en un archivo JSON.
     *
     * @param pokemonName El nombre del Pokémon cuyos datos se desean cargar.
     * @return El objeto PokemonData con los datos almacenados en caché.
     * @throws IOException Si no se puede leer el archivo de caché.
     */
    public static PokemonData loadCache(String pokemonName) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        // Leer el archivo JSON y convertirlo en un objeto PokemonData
        return mapper.readValue(new File(DIR_CACHE + pokemonName + ".json"), PokemonData.class);

    }

    /**
     * Carga la ruta de la imagen guardada localmente en caché de un Pokémon.
     *
     * @param pokemonName El nombre del Pokémon cuya imagen se desea cargar.
     * @return La ruta local de la imagen del Pokémon en formato de URL de archivo.
     */
    public static String loadImageCache(String pokemonName){
        String pokemonImage = "file:" + DIR_CACHE + pokemonName + "_image.png";
        return pokemonImage;
    }

    /**
     * Elimina todos los archivos de caché almacenados en el directorio de caché.
     * Si el directorio no existe, se registra un error.
     */
    public static void deleteCache() {
        File cacheDirectory = new File(DIR_CACHE);

        // Verificar si el directorio de caché existe y es un directorio
        if(cacheDirectory.exists() && cacheDirectory.isDirectory()){

            // Obtener la lista de archivos dentro del directorio de caché
            File[] cacheFiles = cacheDirectory.listFiles();
            if (cacheFiles != null){
                // Eliminar cada archivo dentro del directorio
                for (File file : cacheFiles){
                    if (file.isFile()){
                        file.delete();
                    }
                }
            }
        }else{
            // Si no existe el directorio de caché, registrar un error
            ErrorLogger.saveErrorLog("Error deleting cache. Cache directory does not exist: " + DIR_CACHE);
        }
    }
}
