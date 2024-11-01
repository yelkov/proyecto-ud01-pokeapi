package edu.badpals.pokeapi.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase responsable de gestionar el registro de errores en un archivo de log.
 *
 * Permite guardar mensajes de error junto con la fecha y hora
 * en que ocurrieron
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 * @use Utiliza el método saveErrorLog para registrar mensajes de error en el archivo de log.
 */
public class ErrorLogger {
    // Ruta donde se almacenará el archivo de log de errores
    private static String LOG_FILE_PATH = ".appData/error.log";

    /**
     * Guarda un mensaje de error en un archivo de log, incluyendo la fecha y hora en que ocurrió.
     * Si el archivo no existe, lo crea; si ya existe, añade el mensaje al final.
     *
     * @param message El mensaje de error que se desea guardar.
     */
    public static void saveErrorLog(String message){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_FILE_PATH), Charset.defaultCharset(),StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Define el formato de la fecha y hora (e.g., "2024-10-20 16:30:45")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            writer.write("\n" + message +" - " + formattedDateTime);
        } catch (IOException e) {
            // Si hay un problema al guardar el log, se muestra el error en la consola
            System.out.println("Error saving log file.");
            e.printStackTrace();
        }
    }
}
