package edu.badpals.pokeapi;

import edu.badpals.pokeapi.controller.AppController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;
/**
 * Clase principal de la aplicación búsqueda de Pokémon, que extiende la clase {@code javafx.application.Application}.
 * Esta clase gestiona la inicialización y visualización de la interfaz gráfica de usuario (GUI).
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class Application extends javafx.application.Application {

    /**
     * Method que se invoca al iniciar la aplicación.
     * Carga la vista desde el archivo FXML, establece el título de la ventana,
     * carga las fuentes necesarias y aplica los estilos CSS.
     *
     * @param stage La ventana principal de la aplicación, proporcionada por JavaFX.
     * @throws IOException Si hay un error al cargar el archivo FXML o las fuentes.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);

        // Cargar las fuentes personalizadas para la aplicación
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pokemon_Hollow.ttf"), 40);
        Font.loadFont(getClass().getResourceAsStream("/fonts/pokemon-gb.ttf"), 40);

        // Aplicar la hoja de estilos CSS a la escena
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        // Configurar el título y la escena de la ventana
        stage.setTitle("Pokemon Searcher");
        Image icon = new Image(String.valueOf(getClass().getResource("/images/pika.png")));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        // Guardar el estado de la aplicación cuando la ventana se cierra
        stage.setOnCloseRequest(e -> {
            AppController.saveState();
        });
    }

    /**
     * Method principal de la aplicación, que inicia la ejecución de la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }
}