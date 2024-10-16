module edu.badpals.pokeapi {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.badpals.pokeapi to javafx.fxml;
    exports edu.badpals.pokeapi;
}