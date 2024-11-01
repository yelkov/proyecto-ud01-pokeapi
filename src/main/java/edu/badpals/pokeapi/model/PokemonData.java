package edu.badpals.pokeapi.model;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que encapsula la información de un Pokémon, incluyendo sus datos básicos,
 * áreas donde puede encontrarse en el juego y su imagen. Esta clase es {@link Serializable} para
 * que pueda ser almacenada o transferida fácilmente.
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 */
public class PokemonData implements Serializable {
    private static final long serialVersionUID = 1L; // Versión serial para compatibilidad

    private Pokemon pokemon; // Información del Pokémon: id, nombre oficial y nombre en varios idiomas
    private List<Area> areas; // Lista de áreas donde puede encontrarse el Pokémon
    private PokemonImage pokemonImage; // Imagen del Pokémon

    /**
     * Constructor vacío. Utilizado principalmente para deserialización o inicialización sin datos.
     */
    public PokemonData() {
    }

    /**
     * Constructor que inicializa todos los atributos de la clase.
     *
     * @param pokemon      El objeto {@link Pokemon} que contiene la información básica del Pokémon.
     * @param areas        Una lista de objetos {@link Area} que representan las áreas donde el Pokémon puede ser encontrado.
     * @param pokemonImage El objeto {@link PokemonImage} que contiene la imagen del Pokémon.
     */
    public PokemonData(Pokemon pokemon, List<Area> areas, PokemonImage pokemonImage) {
        this.pokemon = pokemon;
        this.areas = areas;
        this.pokemonImage = pokemonImage;
    }

    /**
     * Obtiene el objeto {@link Pokemon} que contiene los datos del Pokémon.
     *
     * @return El objeto {@link Pokemon} con los datos básicos del Pokémon.
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Establece los datos del Pokémon.
     *
     * @param pokemon El objeto {@link Pokemon} con los datos básicos del Pokémon.
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * Obtiene la lista de áreas donde el Pokémon puede ser encontrado.
     *
     * @return Una lista de objetos {@link Area} que representan las áreas de encuentro del Pokémon.
     */
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * Establece la lista de áreas donde el Pokémon puede ser encontrado.
     *
     * @param areas Una lista de objetos {@link Area}.
     */
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    /**
     * Obtiene la imagen del Pokémon.
     *
     * @return El objeto {@link PokemonImage} que contiene la imagen del Pokémon.
     */
    public PokemonImage getPokemonImage() {
        return pokemonImage;
    }

    /**
     * Establece la imagen del Pokémon.
     *
     * @param pokemonImage El objeto {@link PokemonImage} que contiene la imagen del Pokémon.
     */
    public void setPokemonImage(PokemonImage pokemonImage) {
        this.pokemonImage = pokemonImage;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link PokemonData},
     * útil para depuración y registro de la información del Pokémon.
     *
     * @return Una cadena que contiene los datos del Pokémon, sus áreas y su imagen.
     */
    @Override
    public String toString() {
        return "PokemonData{" +
                "pokemonImage=" + pokemonImage +
                ", areas=" + areas +
                ", pokemon=" + pokemon +
                '}';
    }
}
