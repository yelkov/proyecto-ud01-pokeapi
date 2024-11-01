package edu.badpals.pokeapi.model.area;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Clase que representa un área en la que se puede encontrar un Pokémon dentro del juego.
 * Esta clase encapsula un objeto de tipo {@link LocationArea} donde se almacena el nombre del área.
 *
 * Esta clase es utilizada para almacenar y manejar la información de las áreas de aparición
 * de un Pokémon en diferentes ubicaciones del mapa del juego.
 *
 * La clase implementa la interfaz {@link Serializable}, lo que permite que los objetos de
 * esta clase puedan ser serializados para su almacenamiento o transmisión.
 *
 * @see LocationArea
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class Area implements Serializable {

    // Objeto que representa el área específica de la localización dentro del juego.
    @JsonProperty("location_area")
    private LocationArea loc;

    /**
     * Obtiene la ubicación del área donde se encuentra el Pokémon.
     *
     * @return El objeto {@link LocationArea} que representa la localización del área.
     */
    public LocationArea getLocation_area() {
        return loc;
    }

    /**
     * Establece la ubicación del área donde se encuentra el Pokémon.
     *
     * @param loc Un objeto {@link LocationArea} que representa la localización del área.
     */
    public void setLocationArea(LocationArea loc) {
        this.loc = loc;
    }

    /**
     * Obtiene el nombre legible del área, reemplazando los guiones por espacios en blanco.
     *
     * Este method es útil para mostrar nombres de áreas de forma más legible para los usuarios.
     *
     * @return El nombre legible del área, con guiones reemplazados por espacios.
     */
    public String obtainName() {
        return getLocation_area().getName().replace("-", " ");
    }

    /**
     * Proporciona una representación en cadena del objeto {@link Area}.
     *
     * Esta representación incluye el nombre del área de ubicación.
     *
     * @return Una cadena con los detalles del área.
     */
    @Override
    public String toString() {
        return "Area{" +
                "locationArea='" + loc + '\'' +
                '}';
    }
}
