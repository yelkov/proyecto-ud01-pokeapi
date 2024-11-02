package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.badpals.pokeapi.model.languages.FlavourTextEntry;
import edu.badpals.pokeapi.model.languages.ForeignName;

import javax.swing.text.FlowView;
import java.io.Serializable;
import java.util.ArrayList;
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

    @JsonProperty("flavor_text_entries")
    private List<FlavourTextEntry> descriptions;

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

    public List<FlavourTextEntry> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<FlavourTextEntry> descriptions) {
        this.descriptions = descriptions;
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

    public List<String> obtainVersions(){
        List<String> versions = new ArrayList<>();
        for (FlavourTextEntry entry : getDescriptions()){
            String version = entry.getVersion().getName();
            if (!versions.contains(version)){
                versions.add(version);
            }
        }
        return versions.stream().sorted().toList();
    }

    public Map<String, String> obtainDescriptionsDictionary(){
        Map<String,String> dictionary = new HashMap<>();
        for (FlavourTextEntry entry : getDescriptions()){
            dictionary.putIfAbsent(entry.getLanguage().getName()+entry.getVersion().getName(),entry.getDescription());
        }
        return dictionary;
    }

    public String transformLanguage2Code(String language){
        String code = "";
        switch (language){
            case "korean":
                code = "ko";
                break;
            case "english":
                code = "en";
                break;
            case "french":
                code = "fr";
                break;
            case "german":
                code = "de";
                break;
            case "español":
                code = "es";
                break;
            case "italiano":
                code = "it";
                break;
            case "japanese":
                code = "ja";
                break;
            case "simplified chinese":
                code = "zh-Hans";
                break;
            default:
                code = "other";
                break;
        }
        return code;
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
                ", descriptions=" + descriptions +
                '}';
    }
}