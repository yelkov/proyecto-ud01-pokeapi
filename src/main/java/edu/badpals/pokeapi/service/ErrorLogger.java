package edu.badpals.pokeapi.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger {
    private static String LOG_FILE_PATH = ".appData/error.log";

    public static void saveErrorLog(String message){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE_PATH), Charset.defaultCharset(),StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            writer.write("\n" + message +" - " + formattedDateTime);
        } catch (IOException e) {
            System.out.println("Error saving log file.");
            e.printStackTrace();
        }
    }
}
