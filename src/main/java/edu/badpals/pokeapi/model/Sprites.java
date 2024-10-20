package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Clase que representa los sprites (imágenes) de un Pokémon, obtenidos desde la API.
 *
 * Esta clase encapsula la imagen frontal por defecto de un Pokémon, con la capacidad de ser extendida
 * si se desean añadir más vistas o versiones de los sprites. Está encapsulada en la clase {@link PokemonImage}
 *
 * La clase implementa la interfaz {@link Serializable}, lo que permite la serialización
 * de los objetos de esta clase, facilitando su almacenamiento y recuperación.
 *
 * @see PokemonImage
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class Sprites implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    // URL de la imagen frontal por defecto del Pokémon.
    @JsonProperty("front_default")
    private String front_default;

    /**
     * Constructor por defecto.
     */
    public Sprites() {
    }

    /**
     * Obtiene la URL del sprite frontal por defecto del Pokémon.
     *
     * @return Una cadena que representa la URL de la imagen frontal por defecto.
     */
    public String getFront_default() {
        return front_default;
    }

    /**
     * Establece la URL del sprite frontal por defecto del Pokémon.
     *
     * @param front_default La URL de la imagen frontal por defecto.
     */
    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link Sprites}.
     *
     * Esta representación incluye la URL del sprite frontal por defecto del Pokémon.
     *
     * @return Una cadena que contiene la URL de la imagen frontal por defecto del Pokémon.
     */
    @Override
    public String toString() {
        return "Sprites{" +
                "front_default='" + front_default + '\'' +
                '}';
    }
}
