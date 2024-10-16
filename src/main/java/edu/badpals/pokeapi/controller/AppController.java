package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.service.APIPetitions;
import javafx.fxml.FXML;
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

    public static Pokemon pokemon;
    public static int id;
    public static List<Area> areas;
    public static int currentArea = 0;


    @FXML
    protected void cargarPokemon() {
        pokemon = APIPetitions.askAPIforPokemon(pokemonName.getText());
        id = pokemon.getId();
        pokemonId.setText(String.valueOf(id));
        englishName.setText(pokemon.getNameDictionary().get("english"));
        koreanName.setText(pokemon.getNameDictionary().get("korean"));
        japaneseName.setText(pokemon.getNameDictionary().get("japanese"));
        areas = APIPetitions.askAPIforArea(id);
    }

    public void buscarArea(){
        if (areas.size() == 0){
            pokemonLocation.setText("No se encuentra en estado salvaje");//Es posible que esté vacío, corregir
        } else if (areas.size() <= currentArea + 1) {
            currentArea = 0;
            pokemonLocation.setText(areas.get(currentArea).getName());
            currentArea++;
        } else{
            pokemonLocation.setText(areas.get(currentArea).getName());
            currentArea++;
        }

    }
}
