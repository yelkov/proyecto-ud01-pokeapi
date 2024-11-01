package edu.badpals.pokeapi.model.area;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Clase que representa un idioma en el que puede estar traducido el nombre de un Pokémon.
 *
 * Esta clase es utilizada dentro de la clase {@link ForeignName} para definir el idioma
 * asociado a un nombre de Pokémon.
 *
 * La clase implementa la interfaz {@link Serializable}, lo que permite que los objetos de
 * esta clase puedan ser serializados para su almacenamiento o transmisión.
 *
 * @see ForeignName
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class Language implements Serializable {

    // Nombre del idioma, representado como un código de idioma (por ejemplo, "en" para inglés).
    @JsonProperty("name")
    private String name;

    /**
     * Constructor por defecto. Utilizado principalmente para la deserialización de objetos.
     */
    public Language() {
    }

    /**
     * Obtiene el nombre del idioma.
     *
     * @return El nombre del idioma, un código ISO (como "en" para inglés).
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del idioma.
     *
     * @param name El código ISO o nombre del idioma.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link Language}.
     * Esta representación incluye el nombre del idioma.
     *
     * @return Una cadena con el nombre del idioma.
     */
    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                '}';
    }
}