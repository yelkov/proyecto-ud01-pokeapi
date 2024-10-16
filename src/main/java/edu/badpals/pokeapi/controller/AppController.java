package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.service.APIPetitions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    protected void cargarPokemon() {
        Pokemon pokemon = APIPetitions.askAPIforPokemon(pokemonName.getText());
        pokemonId.setText(String.valueOf(pokemon.getId()));
        englishName.setText(pokemon.getNameDictionary().get("english"));
        koreanName.setText(pokemon.getNameDictionary().get("korean"));
        japaneseName.setText(pokemon.getNameDictionary().get("japanese"));
    }
}
