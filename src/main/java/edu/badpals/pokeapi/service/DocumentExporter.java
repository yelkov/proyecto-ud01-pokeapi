package edu.badpals.pokeapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.ForeignName;
import edu.badpals.pokeapi.model.PokemonData;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Clase responsable de exportar datos de un Pokémon a diferentes formatos de archivo.
 *
 * Permite exportar un objeto {@link PokemonData} a formatos JSON, binario, texto y XML.
 * Cada método de exportación toma como entrada el objeto Pokémon y la ruta donde se
 * guardará el archivo, devolviendo un booleano que indica si la operación fue exitosa.
 *
 * @author David Búa @BuaTeijeiro
 * @author Yelko Veiga @yelkov
 * @version 1.0
 * @use Utiliza los métodos exportToJson, exportToBin, exportToTxt y exportToXml para
 * exportar los datos del Pokémon en el formato deseado.
 */
public class DocumentExporter {
    /**
     * Exporta los datos de un Pokémon a un archivo JSON.
     *
     * @param pokemonData El objeto PokemonData a exportar.
     * @param path La ruta donde se guardará el archivo JSON (sin extensión).
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
    public static boolean exportToJson(PokemonData pokemonData, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + ".json"), pokemonData);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Exporta los datos de un Pokémon a un archivo binario.
     *
     * @param pokemonData El objeto PokemonData a exportar.
     * @param path La ruta donde se guardará el archivo binario (sin extensión).
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
    public static boolean exportToBin(PokemonData pokemonData, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + ".bin"))) {
            oos.writeObject(pokemonData);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Exporta los datos de un Pokémon a un archivo de texto.
     *
     * @param pokemonData El objeto PokemonData a exportar.
     * @param path La ruta donde se guardará el archivo de texto (sin extensión).
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
    public static boolean exportToTxt(PokemonData pokemonData, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + ".txt"))) {
            // Convertir el objeto PokemonData a texto y escribirlo en un archivo
            String pokemonText = pokemonData.toString();
            bw.write(pokemonText);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Exporta los datos de un Pokémon a un archivo XML, usando un árbol DOM.
     *
     * @param pokemonData El objeto PokemonData a exportar.
     * @param path La ruta donde se guardará el archivo XML (sin extensión).
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
    public static boolean exportToXml(PokemonData pokemonData, String path) {
        try {
            // Crear un documento XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(null, "pokemonData", null);
            doc.setXmlVersion("1.0");

            // Raíz del xml, PokemonData
            Element root = doc.getDocumentElement();

            // Crear elementos para las propiedades del Pokémon
            Element elementPokemon = doc.createElement("pokemon");
            root.appendChild(elementPokemon);
            Element elementIdPokemon = doc.createElement("id");
            elementPokemon.appendChild(elementIdPokemon);
            elementIdPokemon.appendChild(doc.createTextNode(String.valueOf(pokemonData.getPokemon().getId())));
            Element elementNamePokemon = doc.createElement("name");
            elementPokemon.appendChild(elementNamePokemon);
            elementNamePokemon.appendChild(doc.createTextNode(pokemonData.getPokemon().getName()));
            Element elementForeignNames = doc.createElement("foreignNames");
            elementPokemon.appendChild(elementForeignNames);
            for (ForeignName foreignName : pokemonData.getPokemon().getForeignNames()) {
                Element elementNameFN = doc.createElement("name");
                elementForeignNames.appendChild(elementNameFN);
                elementNameFN.appendChild(doc.createTextNode(foreignName.getName()));
                Element elementLanguageFN = doc.createElement("language");
                elementForeignNames.appendChild(elementLanguageFN);
                Element elementNameLanguage = doc.createElement("name");
                elementLanguageFN.appendChild(elementNameLanguage);
                elementNameLanguage.appendChild(doc.createTextNode(foreignName.obtainLanguageCode()));
            }

            // Crear elementos para las áreas
            Element elementAreas = doc.createElement("areas");
            root.appendChild(elementAreas);
            for (Area area : pokemonData.getAreas()) {
                Element elementLocation = doc.createElement("locationArea");
                elementAreas.appendChild(elementLocation);
                Element elementNameLocationArea = doc.createElement("name");
                elementLocation.appendChild(elementNameLocationArea);
                elementNameLocationArea.appendChild(doc.createTextNode(area.getLocation_area().getName()));
            }

            // Crear elemento para la imagen del Pokémon
            Element elementImage = doc.createElement("pokemonImage");
            root.appendChild(elementImage);
            Element elementSprites = doc.createElement("sprites");
            elementImage.appendChild(elementSprites);
            Element elementDataImg = doc.createElement("front_default");
            elementSprites.appendChild(elementDataImg);
            elementDataImg.appendChild(doc.createTextNode(pokemonData.getPokemonImage().obtainImage()));

            // Guardar el documento XML en un archivo
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File(path + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            return false;
        }
        return true;
    }
}

