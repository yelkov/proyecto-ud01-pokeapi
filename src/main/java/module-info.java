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
}