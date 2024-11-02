module edu.badpals.pokeapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens edu.badpals.pokeapi to javafx.fxml;
    opens edu.badpals.pokeapi.model;
    exports edu.badpals.pokeapi.model;
    exports edu.badpals.pokeapi;
    exports edu.badpals.pokeapi.controller to javafx.fxml;
    opens edu.badpals.pokeapi.controller;
    exports edu.badpals.pokeapi.model.image;
    opens edu.badpals.pokeapi.model.image;
    exports edu.badpals.pokeapi.model.area;
    opens edu.badpals.pokeapi.model.area;
    exports edu.badpals.pokeapi.model.languages;
    opens edu.badpals.pokeapi.model.languages;
}