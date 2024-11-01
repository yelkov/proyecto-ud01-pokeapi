package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.badpals.pokeapi.model.area.ForeignName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa los datos básicos de un Pokémon, incluyendo su ID, nombre,
 * y nombres en otros idiomas. Esta clase es {@link Serializable} para que pueda ser almacenada
 * o transferida fácilmente.
 *
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 */
public class Pokemon implements Serializable {

    @JsonProperty("id")
    private int id; // Identificador único del Pokémon

    @JsonProperty("name")
    private String name; // Nombre del Pokémon

    @JsonProperty("names")
    private List<ForeignName> foreignNames; // Lista de nombres del Pokémon en otros idiomas

    /**
     * Constructor por defecto. Se utiliza principalmente para deserialización
     * o inicialización sin datos.
     */
    public Pokemon() {
    }

    /**
     * Obtiene el identificador único del Pokémon.
     *
     * @return El identificador del Pokémon.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del Pokémon.
     *
     * @param id El nuevo ID del Pokémon.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del Pokémon.
     *
     * @return El nombre del Pokémon.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del Pokémon.
     *
     * @param name El nuevo nombre del Pokémon.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de nombres del Pokémon en varios idiomas.
     *
     * @return Una lista de objetos {@link ForeignName} que representan los nombres del Pokémon en diferentes idiomas.
     */
    public List<ForeignName> getForeignNames() {
        return foreignNames;
    }

    /**
     * Establece la lista de nombres del Pokémon en varios idiomas.
     *
     * @param foreignNames Una lista de objetos {@link ForeignName} que representan los nombres en otros idiomas.
     */
    public void setForeignNames(List<ForeignName> foreignNames) {
        this.foreignNames = foreignNames;
    }

    /**
     * Genera un diccionario que asocia el nombre de cada idioma con su traducción del nombre del Pokémon en ese idioma.
     *
     * @return Un mapa con el nombre del idioma como clave y el nombre del Pokémon en ese idioma como valor.
     */
    public Map<String, String> obtainNameDictionary() {
        Map<String, String> dictionary = new HashMap<>();
        for (ForeignName foreignName : getForeignNames()) {
            dictionary.putIfAbsent(foreignName.obtainLanguageName(), foreignName.getName());
        }
        return dictionary;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link Pokemon}
     *
     * @return Una cadena que contiene los datos del Pokémon, como su ID, nombre oficial y nombres en otros idiomas.
     */
    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foreignNames=" + foreignNames +
                '}';
    }
}