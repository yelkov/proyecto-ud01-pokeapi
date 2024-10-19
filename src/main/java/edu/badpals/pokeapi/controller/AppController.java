package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.Application;
import edu.badpals.pokeapi.auth.LogInManager;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;
import edu.badpals.pokeapi.service.APIPetitions;
import edu.badpals.pokeapi.service.CacheManager;
import edu.badpals.pokeapi.service.DocumentExporter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AppController {
    @FXML
    private TextField pokemonName;

    @FXML
    private Label pokemonId;

    @FXML
    private Label pokemonLocation;

    @FXML
    private ComboBox<String> languages;

    @FXML
    private Label foreignName;

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label errorMessage;

    @FXML
    private ComboBox<String> cmbFormat;

    @FXML
    private TextField pathName;

    @FXML
    private Button btnExport;

    @FXML
    private Label exportMessage;

    @FXML
    private VBox logInArea;

    @FXML
    private VBox exportArea;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private VBox loginFields;

    @FXML
    private Label logInStatus;

    @FXML
    private HBox passwordConfirm;

    @FXML
    private Button btnCheckLogIn;

    @FXML
    private Button btnCheckRegister;

    @FXML
    private TextField passwordRepeated;


    public static PokemonData pokemonData;
    public static Pokemon pokemon;
    public static int id;
    public static List<Area> areas;
    public static int currentArea = 0;

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
    }


    @FXML
    protected void loadPokemon() {
        Boolean loadable = true;
        String name = pokemonName.getText();
        errorMessage.setVisible(false);
        currentArea = 0;
        try{
            pokemonData = CacheManager.loadCache(name);
        } catch (IOException e){
            try {
                pokemonData = APIPetitions.getPokemonData(pokemonName.getText());
                CacheManager.saveCache(pokemonData);
            } catch (IOException notFound){
                loadable = false;
                cleanFields();
                errorMessage.setVisible(true);
            }
        }
        if(loadable) {
            pokemon = pokemonData.getPokemon();
            id = pokemon.getId();
            pokemonId.setText(String.valueOf(id));
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
        }


    public void loadForeignName(){
        try {
            String idioma = languages.getSelectionModel().getSelectedItem();
            foreignName.setText(pokemon.obtainNameDictionary().get(idioma));
        } catch (NullPointerException e){

        }
    }

    public void searchArea(){
        try {
            if (areas.size() == 0) {
                pokemonLocation.setText("No se encuentra en estado salvaje");//Es posible que esté vacío, corregir
            } else {
                pokemonLocation.setText(areas.get(currentArea).obtainName());
            }
        }catch (NullPointerException e){}
    }

    public void getNextArea(){
        currentArea++;
        if (areas.size() <= currentArea){
            currentArea = 0;
        }
        searchArea();
    }

    public void getPreviousArea(){
        currentArea--;
        if(currentArea < 0){
            currentArea = areas.size() - 1;
        }
        searchArea();
    }

    public void showlogIn(){
        loginFields.setManaged(true);
        loginFields.setVisible(true);
        passwordConfirm.setManaged(false);
        passwordConfirm.setVisible(false);
        btnCheckLogIn.setManaged(true);
        btnCheckLogIn.setVisible(true);
        btnCheckRegister.setManaged(false);
        btnCheckRegister.setVisible(false);
    }

    public void showRegister(){
        loginFields.setManaged(true);
        loginFields.setVisible(true);
        passwordConfirm.setManaged(true);
        passwordConfirm.setVisible(true);
        btnCheckLogIn.setManaged(false);
        btnCheckLogIn.setVisible(false);
        btnCheckRegister.setManaged(true);
        btnCheckRegister.setVisible(true);
    }

    public void checkLogIn(){
        boolean isLogInValid = LogInManager.authenticate(userName.getText(), password.getText());
        if (isLogInValid) {
            initLogIn();
        } else {
            password.setText("");
            logInStatus.setManaged(true);
            logInStatus.setVisible(true);
        }
    }

    public void checkRegister(){
        boolean isRegisterValid = LogInManager.signUp(userName.getText(), password.getText());
        System.out.println(userName.getText());
        boolean isPasswordSame = password.getText().equals(passwordRepeated.getText());
        if (isRegisterValid && isPasswordSame){
            initLogIn();
        } else if (!isPasswordSame) {
            logInStatus.setManaged(true);
            logInStatus.setVisible(true);
            logInStatus.setText("Las contraseñas no coinciden");
            password.setText("");
            passwordRepeated.setText("");
        } else if (!isRegisterValid) {
            logInStatus.setManaged(true);
            logInStatus.setVisible(true);
            logInStatus.setText("El usuario ya se halla registrado en la aplicación");
            password.setText("");
            passwordRepeated.setText("");
        }
    }

    public void initLogIn(){
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

    public void logOut(){
        exportArea.setManaged(false);
        exportArea.setVisible(false);
        logInArea.setVisible(true);
        logInArea.setManaged(true);
        showlogIn();
    }



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


    public void cleanFields(){
        pokemonData = null;
        pokemon = null;
        areas = null;
        currentArea = 0;
        pokemonLocation.setText("");
        pokemonId.setText("");
        foreignName.setText("");
        pokemonName.setText("");
        pathName.setText("");
        btnAnterior.setDisable(true);
        btnSiguiente.setDisable(true);
        btnExport.setDisable(true);
        errorMessage.setVisible(false);
        exportMessage.setText("");
    }
}
