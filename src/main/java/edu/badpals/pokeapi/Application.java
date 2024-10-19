package edu.badpals.pokeapi;

import edu.badpals.pokeapi.controller.AppController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pokemon_Hollow.ttf"), 40);
        Font.loadFont(getClass().getResourceAsStream("/fonts/pokemon-gb.ttf"), 40);

        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setTitle("Pokemon Searcher");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest( e -> {
            AppController.saveState();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}