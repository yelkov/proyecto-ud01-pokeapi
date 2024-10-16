module edu.badpals.pokeapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens edu.badpals.pokeapi to javafx.fxml;
    exports edu.badpals.pokeapi;
}