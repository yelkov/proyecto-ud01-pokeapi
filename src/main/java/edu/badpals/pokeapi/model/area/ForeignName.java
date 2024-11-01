package edu.badpals.pokeapi.model.area;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Clase que representa el nombre de un Pokémon en un idioma específico. Incluye
 * tanto el nombre del Pokémon traducido como el idioma en el que está traducido,
 * encapsulado este último como un objeto de tipo {@link Language}.
 *
 * Esta clase es útil para manejar la internacionalización de los nombres de los Pokémon
 * en diferentes lenguajes.Esta clase es {@link Serializable} para que pueda ser almacenada
 * o transferida fácilmente.
 *
 * @see Language
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class ForeignName implements Serializable {

    // Nombre del Pokémon en un idioma específico
    @JsonProperty("name")
    private String name;

    // Idioma asociado al nombre
    @JsonProperty("language")
    private Language language;

    /**
     * Constructor por defecto. Es utilizado principalmente para la deserialización.
     */
    public ForeignName() {
    }

    /**
     * Obtiene el objeto {@link Language} asociado a este nombre.
     *
     * @return El idioma en el que se encuentra el nombre del Pokémon.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Establece el objeto {@link Language} para este nombre.
     *
     * @param language El nuevo idioma para este nombre.
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Obtiene el nombre del Pokémon en un idioma específico.
     *
     * @return El nombre del Pokémon.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del Pokémon en un idioma específico.
     *
     * @param name El nuevo nombre del Pokémon.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el código del idioma de este nombre. El código de idioma es
     * una representación corta como "en" para inglés, "fr" para francés, etc.
     *
     * @return El código del idioma.
     */
    public String obtainLanguageCode() {
        return getLanguage().getName();
    }

    /**
     * Obtiene el nombre completo del idioma basado en su código. Los nombres de los idiomas
     * están predefinidos en este method con los códigos ISO, y si no es un idioma
     * conocido, devuelve "other".
     *
     * @return El nombre completo del idioma, como "english", "french", etc.
     */
    public String obtainLanguageName() {
        String code = obtainLanguageCode();
        String name;
        switch (code) {
            case "ko":
                name = "korean";
                break;
            case "en":
                name = "english";
                break;
            case "fr":
                name = "french";
                break;
            case "de":
                name = "german";
                break;
            case "ja":
                name = "japanese";
                break;
            case "zh-Hans":
                name = "simplified chinese";
                break;
            default:
                name = "other";
                break;
        }
        return name;
    }

    /**
     * Proporciona una representación en cadena del objeto {@link ForeignName}, útil
     * para depuración y visualización de datos.
     *
     * @return Una cadena que contiene el nombre del Pokémon y su idioma asociado.
     */
    @Override
    public String toString() {
        return "ForeignName{" +
                "name='" + name + '\'' +
                ", language=" + obtainLanguageName() +
                '}';
    }
}