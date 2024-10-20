package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.Application;
import edu.badpals.pokeapi.auth.LogInManager;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;
import edu.badpals.pokeapi.service.APIPetitions;
import edu.badpals.pokeapi.service.CacheManager;
import edu.badpals.pokeapi.service.DocumentExporter;
import edu.badpals.pokeapi.service.StateManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Clase AppController.
 * Controlador principal para la interfaz de usuario de la aplicación PokeAPI.
 * Gestiona las interacciones del usuario con la API o el caché, la visualización de la información obtenida,
 * la autenticación de usuarios y la exportación de datos.
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 */
public class AppController {

    // Atributos de la interfaz que representan componentes gráficos de la vista

    @FXML
    private TextField pokemonName;  // Campo de texto donde el usuario ingresa el nombre del Pokémon a buscar

    @FXML
    private Label pokemonId;        // Etiqueta para mostrar el Id del Pokémon

    @FXML
    private Label pokemonLocation;  // Etiqueta para mostrar la ubicación del Pokémon

    @FXML
    private ComboBox<String> languages;  // ComboBox para seleccionar el idioma del nombre extranjero del Pokémon

    @FXML
    private Label foreignName;  // Etiqueta para mostrar el nombre del Pokémon en el idioma extranjero escogido

    @FXML
    private Button btnAnterior, btnSiguiente;  // Botones para navegar entre ubicaciones del Pokémon

    @FXML
    private Label errorMessage;  // Etiqueta para mostrar mensajes de error en la búsqueda del Pokémon

    @FXML
    private ComboBox<String> cmbFormat;  // ComboBox para seleccionar el formato de exportación de datos

    @FXML
    private TextField pathName;  // Campo de texto para ingresar la ruta de exportación

    @FXML
    private Button btnExport;  // Botón para exportar datos del Pokémon

    @FXML
    private Label exportMessage;  // Etiqueta para mostrar mensajes relacionados con la exportación

    @FXML
    private VBox logInArea, exportArea;  // Áreas de la interfaz para el inicio de sesión y la exportación de datos

    @FXML
    private TextField userName;  // Campo de texto para ingresar el nombre de usuario

    @FXML
    private PasswordField password;  // Campo de texto para ingresar la contraseña

    @FXML
    private VBox loginFields;  // Sección del formulario para el inicio de sesión

    @FXML
    private Label logInStatus;  // Etiqueta para mostrar mensajes de estado del inicio de sesión

    @FXML
    private HBox passwordConfirm;  // Contenedor para confirmar la contraseña en el registro

    @FXML
    private Button btnCheckLogIn, btnCheckRegister;  // Botones para iniciar sesión o registrarse

    @FXML
    private TextField passwordRepeated;  // Campo de texto para repetir la contraseña en el registro

    @FXML
    private Label currentPokemonName;  // Etiqueta para mostrar el nombre del Pokémon actualmente cargado

    @FXML
    private ImageView pokemonImage, logo;  // Vistas de imagen para mostrar el logo de la aplicación y la imagen del Pokémon

    // Variables estáticas para mantener el estado actual del Pokémon, su área de ubicación y el usuario logeado
    public static PokemonData pokemonData;
    public static Pokemon pokemon;
    public static int id;
    public static List<Area> areas;
    public static int currentArea = 0;
    public static String currentUsername = null;

    /**
     * Inicializa el contenido de las ComboBox de selección de idioma y formato de exportación,
     * así como el logo de la aplicación.
     */
    @FXML
    private void initialize() {
        languages.setItems(FXCollections.observableArrayList(
                "english","japanese","korean","french","german","simplified chinese"
        ));
        languages.setValue("english");

        cmbFormat.setItems(FXCollections.observableArrayList(
                "json","xml","txt","bin"
        ));
        cmbFormat.setValue("json");
        logo.setImage(new Image("file:src/main/resources/images/pokeapi.png"));
    }

    /**
     * Permite setear la visibilidad de un elemento como visible o invisible y sin ocupar espacio
     *
     * @param node Nodo de JavaFX cuya visibilidad se quiere cambiar
     * @param visible valor booleano que indica si queremos que sea visible o no
     */
    @FXML
    private void show(Node node, boolean visible){
        node.setVisible(visible);
        node.setManaged(visible);
    }


    /**
     * Permite cargar la información del pokémon cuyo nombre se introduce en el cuadro de texto
     * Si los datos se encunetran en el caché, se cargan de ahí, de lo contrario, se hace la petición a la API
     * Si dicha información no se puede cargar, se imprime un mensaje de error.
     */
    @FXML
    protected void loadPokemon() {
        Boolean loadable = true;
        String name = pokemonName.getText().toLowerCase();
        show(errorMessage, false);
        currentArea = 0;
        try{
            pokemonData = CacheManager.loadCache(name);
        } catch (IOException e){
            try {
                if (!name.equals("")) {
                    pokemonData = APIPetitions.getPokemonData(name);
                    CacheManager.saveCache(pokemonData);
                } else {
                    loadable = false;
                }

            } catch (IOException notFound){
                loadable = false;
                cleanFields();
                show(errorMessage,true);
            }
        }
        if(loadable) {
            printPokemonInfo();
        }
        }

    /**
     * Muestra la información del pokemon cargado en memoria en los correspondientes elementos de la interfaz.
     * También se asegura de que los botones para moverse entre áreas se bloqueen o se activen, según se requiera.
     */
    public void printPokemonInfo(){
        pokemon = pokemonData.getPokemon();
        id = pokemon.getId();
        show(currentPokemonName, true);
        currentPokemonName.setText(pokemon.getName().toUpperCase());
        pokemonId.setText(String.valueOf(id));
        show(pokemonImage, true);
        pokemonImage.setImage(new Image(CacheManager.loadImageCache(pokemon.getName())));
        areas = pokemonData.getAreas();
        if (areas.size() > 1) {
            btnAnterior.setDisable(false);
            btnSiguiente.setDisable(false);
        } else {
            btnAnterior.setDisable(true);
            btnSiguiente.setDisable(true);
        }
        btnExport.setDisable(false);
        loadForeignName();
        searchArea();
    }

    /**
     * Muestra en la interfaz el nombre correspondiente al idioma seleccionado en el ComboBox.
     */
    public void loadForeignName(){
        try {
            String idioma = languages.getSelectionModel().getSelectedItem();
            foreignName.setText(pokemon.obtainNameDictionary().get(idioma));
        } catch (NullPointerException e){

        }
    }

    /**
     * Carga la información por defecto de la área, ya sea que no se encuentra en estado salvaje, o la correspondiente al índice cargado en memoria.
     */
    public void searchArea(){
        try {
            if (areas.size() == 0) {
                pokemonLocation.setText("No se encuentra en estado salvaje");//Es posible que esté vacío, corregir
            } else {
                pokemonLocation.setText(areas.get(currentArea).obtainName());
            }
        }catch (NullPointerException e){
            //Si no hay pokémon cargado en memoria, no se hace nada
        }
    }

    /**
     * Mueve el cursor de área actual en memoria añadiendo 1, o seteándolo a 0 si llega al final,
     * luego llama a la función área con ese nuevo índice.
     */
    public void getNextArea(){
        currentArea++;
        if (areas.size() <= currentArea){
            currentArea = 0;
        }
        searchArea();
    }

    /**
     * Mueve el cursor de área actual en memoria restando 1, o seteándolo al último si llega al principio,
     * luego llama a la función área con ese nuevo índice.
     */
    public void getPreviousArea(){
        currentArea--;
        if(currentArea < 0){
            currentArea = areas.size() - 1;
        }
        searchArea();
    }

    /**
     * Muestra la información del login, y oculta la correspondiente exclusivamente al registro.
     */
    public void showlogIn(){
        show(loginFields, true);
        show(btnCheckLogIn, true);
        show(passwordConfirm, false);
        show(btnCheckRegister, false);
    }

    /**
     * Muestra la información del registro, y oculta la correspondiente exclusivamente al login.
     */
    public void showRegister(){
        show(loginFields, true);
        show(passwordConfirm, true);
        show(btnCheckRegister, true);
        show(btnCheckLogIn, false);
    }

    /**
     * Comprueba las credenciales introducidas por el usuario para el login.
     * De ser correctas carga el último estado del usuario, si no, muestra un mensaje de error.
     */
    public void checkLogIn(){
        boolean isLogInValid = LogInManager.authenticate(userName.getText(), password.getText());
        if (isLogInValid) {
            pokemonData = StateManager.loadLastState(userName.getText());
            initLogIn();
            try {
                printPokemonInfo();
            } catch (NullPointerException e){}
                //si no pudo cargar el pokemon, no se carga
        } else {
            password.setText("");
            logInStatus.setText("Usuario o contraseña no válidos");
            show(logInStatus, true);
        }
    }

    /**
     * Comprueba que el registro de un nuevo usuario es válido.
     * De serlo carga la pantalla con el menú de exportaciones, si no, muestra un mensaje de error.
     */
    public void checkRegister(){
        boolean isPasswordSame = password.getText().equals(passwordRepeated.getText());
        if (isPasswordSame){
            if (LogInManager.signUp(userName.getText(), password.getText())){
                initLogIn();
            } else {
                show(logInStatus, true);
                logInStatus.setText("El usuario ya se halla registrado en la aplicación");
                password.setText("");
                passwordRepeated.setText("");
            }
        } else {
            show(logInStatus, true);
            logInStatus.setText("Las contraseñas no coinciden");
            password.setText("");
            passwordRepeated.setText("");
        }
    }

    /**
     * Activa el login, ocultándose los campos de inicio de sesión o registro, y mostrando el menú de exportaciones.
     */
    public void initLogIn(){
        currentUsername = userName.getText();
        logInArea.setVisible(false);
        logInArea.setManaged(false);
        exportArea.setManaged(true);
        exportArea.setVisible(true);
        logInStatus.setManaged(false);
        logInStatus.setVisible(false);
        userName.setText("");
        password.setText("");
        passwordRepeated.setText("");
    }


    /**
     * Permite hacer el cierre de sesión del usuario, para lo que guarda el estado actual.
     * Además oculta el menú de exportaciones y muestra el de login.
     */
    public void logOut(){
        show(exportArea, false);
        show(logInArea, true);
        AppController.saveState();
        currentUsername = null;
        showlogIn();
        cleanFields();
    }


    /**
     * Exporta el pokemon cargado en memoria en la ruta indicada y en el formato escogido.
     */
    public void export() {
        String path = pathName.getText();
        String extension = cmbFormat.getSelectionModel().getSelectedItem();
        boolean isExportOk = false;
        switch (extension) {
            case "json":
                isExportOk = DocumentExporter.exportToJson(pokemonData, path);
                break;
            case "xml":
                isExportOk = DocumentExporter.exportToXml(pokemonData, path);
                break;
            case "txt":
                isExportOk = DocumentExporter.exportToTxt(pokemonData, path);
                break;
            case "bin":
                isExportOk = DocumentExporter.exportToBin(pokemonData, path);
                break;
            default:
                break;
        }
        if (isExportOk){
            exportMessage.setText("La exportación se ha realizado correctamente");
        } else {
            exportMessage.setText("Error al realizar la exportación");
        }
    }

    /**
     * Limpia los campos de la interfaz sobre la información del pokémon o los mensajes de error
     * para mostrar el estado inicial de la aplicación.
     */
    public void cleanFields(){
        pokemonData = null;
        pokemon = null;
        areas = null;
        currentArea = 0;
        currentPokemonName.setText("");
        show(currentPokemonName, false);
        pokemonLocation.setText("");
        pokemonId.setText("");
        foreignName.setText("");
        pokemonName.setText("");
        pathName.setText("");
        btnAnterior.setDisable(true);
        btnSiguiente.setDisable(true);
        btnExport.setDisable(true);
        show(errorMessage, false);
        exportMessage.setText("");
        show(pokemonImage, false);
        pokemonImage.setImage(new Image("file: "));
    }

    /**
     * Borra toda la información guardada en caché
     */
    public void deleteCache(){
        CacheManager.deleteCache();
    }


    /**
     * Guarda la información sobre el pokémon actual para el usuario logeado en el momento de llamar a la función.
     */
    public static void saveState(){
        if ((currentUsername != null)){
            StateManager.saveState(pokemonData, currentUsername);
        }
    }

}
