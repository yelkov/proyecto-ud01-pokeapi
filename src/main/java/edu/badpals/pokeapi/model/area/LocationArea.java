package edu.badpals.pokeapi.model.area;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Clase que representa un área de localización específica en la que se puede encontrar un Pokémon.
 *
 * Esta clase almacena el nombre del área en el mapa del juego donde se puede encontrar un Pokémon.
 * Esta clase está encapsulada dentro de la clase {@link Area}.
 *
 * La clase implementa la interfaz {@link Serializable}, lo que permite la serialización de los objetos
 * de esta clase, facilitando su almacenamiento y recuperación.
 *
 * @see Area
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class LocationArea implements Serializable {

    // Nombre del área de localización donde se encuentra el Pokémon.
    @JsonProperty("name")
    private String name;

    /**
     * Obtiene el nombre del área de localización.
     *
     * @return El nombre del área de localización.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del área de localización.
     *
     * @param name El nombre del área de localización.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link LocationArea}.
     *
     * Esta representación incluye el nombre del área de localización.
     *
     * @return Una cadena que contiene el nombre del área.
     */
    @Override
    public String toString() {
        return "LocationArea{" +
                "name='" + name + '\'' +
                '}';
    }
}
