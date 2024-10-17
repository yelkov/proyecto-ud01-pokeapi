package edu.badpals.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("names")
    private List<ForeignName> foreignNames;

    public Pokemon(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ForeignName> getForeignNames() {
        return foreignNames;
    }

    public Map<String,String> obtainNameDictionary(){
        Map<String,String> dictionary = new HashMap<>();
        for (ForeignName foreignName : getForeignNames()){
            dictionary.putIfAbsent(foreignName.obtainLanguageName(), foreignName.getName());
        }
        return dictionary;
    }

    public void setForeignNames(List<ForeignName> foreignNames) {
        this.foreignNames = foreignNames;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foreignNames=" + foreignNames +
                '}';
    }
}
