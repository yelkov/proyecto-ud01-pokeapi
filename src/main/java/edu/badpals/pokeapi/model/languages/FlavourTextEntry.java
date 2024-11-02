package edu.badpals.pokeapi.model.languages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FlavourTextEntry implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    @JsonProperty("flavor_text")
    private String description;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("version")
    private Version version;

    public FlavourTextEntry(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FlavourTextEntry{" +
                "description='" + description + '\'' +
                ", language=" + language +
                ", version=" + version +
                '}';
    }
}
