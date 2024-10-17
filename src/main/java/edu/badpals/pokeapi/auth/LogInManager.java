package edu.badpals.pokeapi.auth;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Properties;

public class LogInManager {

    public static boolean authenticate(String user, String password) {
        try (FileReader reader = new FileReader("src/main/resources/parametros.properties")){

            Properties properties = new Properties();
            properties.load(reader);

            String correctPassword = properties.getProperty(user);

            String hashedPassword = generateHash(password);

            return hashedPassword.equals(correctPassword);


        } catch (IOException e) {
            System.out.println("Error reading properties file.");
        }
        return false;
    }

    private static String generateHash(String text){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hashing algorithm not found.");;
        }
        return null;
    }
}
