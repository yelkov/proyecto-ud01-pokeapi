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

public class DocumentExporter {
    public static boolean exportToJson(PokemonData pokemonData, String path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + ".json"),pokemonData);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public static boolean exportToBin(PokemonData pokemonData, String path){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + ".bin"))){
            oos.writeObject(pokemonData);
        }catch (IOException e){
            return false;
        }
        return true;
    }
    public static boolean exportToTxt(PokemonData pokemonData, String path){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path + ".txt"))){
            String pokemonText = pokemonData.toString();
            bw.write(pokemonText);
        }catch (IOException e){
            return false;
        }
        return true;
    }

    public static boolean exportToXml(PokemonData pokemonData, String path){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(null,"pokemonData",null);
            doc.setXmlVersion("1.0");

            Element root = doc.getDocumentElement(); //root for PokemonData

            //Start with Pokemon properties
            Element elementPokemon = doc.createElement("pokemon"); //Element for Pokemon
            root.appendChild(elementPokemon);
            Element elementIdPokemon = doc.createElement("id");
            elementPokemon.appendChild(elementIdPokemon);
            elementIdPokemon.appendChild(doc.createTextNode(String.valueOf(pokemonData.getPokemon().getId())));
            Element elementNamePokemon = doc.createElement("name");
            elementPokemon.appendChild(elementNamePokemon);
            elementNamePokemon.appendChild(doc.createTextNode(pokemonData.getPokemon().getName()));
            Element elementForeignNames = doc.createElement("foreignNames");
            elementPokemon.appendChild(elementForeignNames);
            for (ForeignName foreignName : pokemonData.getPokemon().getForeignNames()){
                Element elementNameFN = doc.createElement("name");
                elementForeignNames.appendChild(elementNameFN);
                elementNameFN.appendChild(doc.createTextNode(foreignName.getName()));
                Element elementLanguageFN = doc.createElement("language");
                elementForeignNames.appendChild(elementLanguageFN);
                Element elementNameLanguage = doc.createElement("name");
                elementLanguageFN.appendChild(elementNameLanguage);
                elementNameLanguage.appendChild(doc.createTextNode(foreignName.obtainLanguageCode()));
            }
            //creating Areas elements
            Element elementAreas = doc.createElement("areas");
            root.appendChild(elementAreas);
            for (Area area : pokemonData.getAreas()){
                Element elementLocation = doc.createElement("locationArea");
                elementAreas.appendChild(elementLocation);
                Element elementNameLocationArea = doc.createElement("name");
                elementLocation.appendChild(elementNameLocationArea);
                elementNameLocationArea.appendChild(doc.createTextNode(area.getLocation_area().getName()));
            }
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File(path + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,result);

        } catch (ParserConfigurationException | TransformerException e) {
            return false;
        }
        return true;
    }
}
