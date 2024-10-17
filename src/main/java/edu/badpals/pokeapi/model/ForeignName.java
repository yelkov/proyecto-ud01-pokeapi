package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForeignName{
    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private Language language;

    public ForeignName(){}

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String obtainLanguageCode(){
        return getLanguage().getName();
    }

    public String obtainLanguageName(){
        String code = obtainLanguageCode();
        String name;
        switch (code){
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

    @Override
    public String toString() {
        return "ForeignName{" +
                "name='" + name + '\'' +
                ", language=" + obtainLanguageName() +
                '}';
    }
}