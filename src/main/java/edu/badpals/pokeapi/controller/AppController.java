package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;
import edu.badpals.pokeapi.service.APIPetitions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class AppController {
    @FXML
    private TextField pokemonName;

    @FXML
    private Label pokemonId;

    @FXML
    private Label englishName;

    @FXML
    private Label japaneseName;

    @FXML
    private Label koreanName;

    @FXML
    private Label pokemonLocation;

    @FXML
    private ComboBox<String> idiomas;

    @FXML
    private Label foreignName;

    public static PokemonData pokemonData;
    public static Pokemon pokemon;
    public static int id;
    public static List<Area> areas;
    public static int currentArea = 0;

    @FXML
    private void initialize() {
        idiomas.setItems(FXCollections.observableArrayList(
                "english","japanese","korean","french","german","simplified chinese"
        ));
    }


    @FXML
    protected void cargarPokemon() {
        pokemonData = APIPetitions.getPokemonData(pokemonName.getText());
        pokemon = pokemonData.getPokemon();
        id = pokemon.getId();
        pokemonId.setText(String.valueOf(id));
        areas = pokemonData.getAreas();
    }

    public void cargaNombreExtranjero(){
        String idioma = idiomas.getSelectionModel().getSelectedItem();
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
}
