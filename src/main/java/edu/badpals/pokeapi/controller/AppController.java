package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;
import edu.badpals.pokeapi.service.APIPetitions;
import edu.badpals.pokeapi.service.CacheManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class AppController {
    @FXML
    private TextField pokemonName;

    @FXML
    private Label pokemonId;

    @FXML
    private Label pokemonLocation;

    @FXML
    private ComboBox<String> languages;

    @FXML
    private Label foreignName;

    public static PokemonData pokemonData;
    public static Pokemon pokemon;
    public static int id;
    public static List<Area> areas;
    public static int currentArea = 0;

    @FXML
    private void initialize() {
        languages.setItems(FXCollections.observableArrayList(
                "english","japanese","korean","french","german","simplified chinese"
        ));

    }


    @FXML
    protected void cargarPokemon() {
        String name = pokemonName.getText();
        try{
            pokemonData = CacheManager.loadCache(name);
        } catch (IOException e){
            pokemonData = APIPetitions.getPokemonData(pokemonName.getText());
            CacheManager.saveCache(pokemonData);
        }
        pokemon = pokemonData.getPokemon();
        id = pokemon.getId();
        pokemonId.setText(String.valueOf(id));
        areas = pokemonData.getAreas();
    }

    public void cargaNombreExtranjero(){
        String idioma = languages.getSelectionModel().getSelectedItem();
        foreignName.setText(pokemon.obtainNameDictionary().get(idioma));
    }

    public void buscarArea(){
        if (areas.size() == 0){
            pokemonLocation.setText("No se encuentra en estado salvaje");//Es posible que esté vacío, corregir
        } else if (areas.size() <= currentArea + 1) {
            currentArea = 0;
            pokemonLocation.setText(areas.get(currentArea).obtainName());
            currentArea++;
        } else{
            pokemonLocation.setText(areas.get(currentArea).obtainName());
            currentArea++;
        }

    }

    public void limpiarCampos(){
        pokemonLocation.setText("");
        pokemonId.setText("");
        foreignName.setText("");
        pokemonName.setText("");
    }
}
