package edu.badpals.pokeapi.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Clase que representa la imagen de un Pokémon, obtenida desde la API.
 *
 * Esta clase encapsula las imágenes (sprites) de un Pokémon en diferentes perspectivas.
 * Utiliza la clase {@link Sprites} para acceder a las diferentes imágenes disponibles.
 * En nuestro caso solo vamos a almacenar un único sprite en PokemonImage, el frontal por defecto.
 *
 * La clase implementa la interfaz {@link Serializable}, lo que permite la serialización
 * de los objetos de esta clase, facilitando su almacenamiento y recuperación.
 *
 * @see Sprites
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class PokemonImage implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    // Objeto que contiene los sprites (imágenes) del Pokémon.
    @JsonProperty("sprites")
    private Sprites sprites;

    /**
     * Constructor por defecto.
     */
    public PokemonImage() {
    }

    /**
     * Obtiene los sprites (imágenes) del Pokémon.
     *
     * @return Un objeto {@link Sprites} que contiene las imágenes del Pokémon.
     */
    public Sprites getSprites() {
        return sprites;
    }

    /**
     * Establece los sprites (imágenes) del Pokémon.
     *
     * @param sprites Un objeto {@link Sprites} con las imágenes del Pokémon.
     */
    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    /**
     * Obtiene la URL de la imagen frontal por defecto del Pokémon.
     *
     * Este method devuelve la URL de la imagen del sprite frontal por defecto del Pokémon.
     *
     * @return La URL de la imagen frontal por defecto.
     */
    public String obtainImage() {
        return sprites.getFront_default();
    }

    public String obtainGif(){
        return sprites.getVersions().getGeneration_v().getBlack_white().getAnimated().getFront_default();
    }

    /**
     * Proporciona una representación en cadena del objeto {@link PokemonImage}.
     *
     * Esta representación incluye la referencia a los sprites del Pokémon.
     *
     * @return Una cadena que contiene la información sobre los sprites del Pokémon.
     */
    @Override
    public String toString() {
        return "PokemonImage{" +
                "sprites=" + sprites +
                '}';
    }
}
