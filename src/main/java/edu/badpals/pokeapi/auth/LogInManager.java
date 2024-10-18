package edu.badpals.pokeapi.auth;

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

public class LogInManager {
    private static final String CREDENTIALS_FILE = "src/main/resources/credentials.properties";

    public static boolean authenticate(String user, String password) {
        try (FileReader reader = new FileReader(CREDENTIALS_FILE)){

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

    public static boolean signUp(String user, String password) {
        try (FileReader reader = new FileReader(CREDENTIALS_FILE)) {
            Properties properties = new Properties();
            properties.load(reader);
            if(properties.containsKey(user)) {
                return false;
            }else{
                String hashedPassword = generateHash(password);
                properties.setProperty(user, hashedPassword);
                properties.store(new FileOutputStream(CREDENTIALS_FILE), null);

                return true;
            }
        } catch (IOException e) {
            System.out.println("Error writing properties file.");
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
