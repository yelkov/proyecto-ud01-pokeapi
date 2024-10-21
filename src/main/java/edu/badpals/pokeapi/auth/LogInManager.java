package edu.badpals.pokeapi.auth;

import edu.badpals.pokeapi.service.ErrorLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HexFormat;
import java.util.Properties;

/**
 * Clase que gestiona la autenticación de usuarios, incluyendo el inicio de sesión y la creación de nuevos usuarios.
 *
 * Esta clase se basa en un archivo de propiedades que almacena las credenciales (usuario y contraseñas cifradas)
 * y utiliza el algoritmo de hash SHA-256 para la seguridad de las contraseñas.
 *
 * @author David Búa - @BuaTeijeiro
 * @author Yelko Veiga - @yelkov
 * @version 1.0
 */
public class LogInManager {

    // Ruta al archivo que contiene las credenciales de los usuarios.
    private static final String CREDENTIALS_FILE = "src/main/resources/credentials.properties";

    /**
     * Autentica a un usuario verificando el nombre de usuario y la contraseña proporcionados.
     *
     * @param user El nombre de usuario proporcionado.
     * @param password La contraseña proporcionada (en texto plano).
     * @return {@code true} si el nombre de usuario y la contraseña son correctos, {@code false} en caso contrario.
     */
    public static boolean authenticate(String user, String password) {
        try (FileReader reader = new FileReader(CREDENTIALS_FILE)){

            Properties properties = new Properties();
            properties.load(reader);

            String correctPassword = properties.getProperty(user);

            // Cifra la contraseña proporcionada para compararla con la almacenada
            String hashedPassword = generateHash(password);

            return hashedPassword.equals(correctPassword);

        } catch (IOException e) {
            // En caso de error, registrar el error utilizando el ErrorLogger
            ErrorLogger.saveErrorLog("Error reading properties file.");
        }
        return false;
    }

    /**
     * Registra a un nuevo usuario, almacenando su nombre de usuario y la contraseña cifrada en el archivo de propiedades.
     *
     * @param user El nombre de usuario que desea registrarse.
     * @param password La contraseña del nuevo usuario (en texto plano).
     * @return {@code true} si el registro fue exitoso, {@code false} si el usuario ya existe.
     */
    public static boolean signUp(String user, String password) {
        try (FileReader reader = new FileReader(CREDENTIALS_FILE)) {
            Properties properties = new Properties();
            properties.load(reader);

            // Si el usuario ya existe, retornar false
            if(properties.containsKey(user)) {
                return false;
            }else{
                // Cifra la contraseña y la almacena junto al usuario
                String hashedPassword = generateHash(password);
                properties.setProperty(user, hashedPassword);
                properties.store(new FileOutputStream(CREDENTIALS_FILE), null);

                return true;
            }
        } catch (IOException e) {
            // En caso de error, registrar el error utilizando el ErrorLogger
            ErrorLogger.saveErrorLog("Error writing properties file.");
        }
        return false;
    }

    /**
     * Genera un hash SHA-256 para la contraseña proporcionada.
     *
     * @param text La contraseña en texto plano.
     * @return El hash de la contraseña en formato hexadecimal.
     */
    private static String generateHash(String text){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            // Formatea el hash en una cadena hexadecimal
            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException e) {
            // Si el algoritmo de hashing no está disponible, registrar el error
            ErrorLogger.saveErrorLog("Hashing algorithm not found.");
        }
        return null;
    }
}
