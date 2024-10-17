package edu.badpals.pokeapi.service;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.model.PokemonData;

import java.util.List;

public class TestCacheExporter {
    public static void main(String[] args) {
        PokemonData bulbasaur = APIPetitions.getPokemonData("bulbasaur");
        System.out.println(bulbasaur);

        System.out.println("");
        CacheManager.saveCache(bulbasaur);

        PokemonData dataLoaded = CacheManager.loadCache("bulbasaur");
        System.out.println(dataLoaded);

        DocumentExporter.exportToJson(dataLoaded,"G:\\Mi unidad\\DAM\\2_DAM_G\\AD\\PROYECTO API\\"+dataLoaded.getPokemon().getName());
        DocumentExporter.exportToBin(dataLoaded,"G:\\Mi unidad\\DAM\\2_DAM_G\\AD\\PROYECTO API\\"+dataLoaded.getPokemon().getName());
        DocumentExporter.exportToTxt(dataLoaded,"G:\\Mi unidad\\DAM\\2_DAM_G\\AD\\PROYECTO API\\"+dataLoaded.getPokemon().getName());
        DocumentExporter.exportToXml(dataLoaded,"G:\\Mi unidad\\DAM\\2_DAM_G\\AD\\PROYECTO API\\"+dataLoaded.getPokemon().getName());
        ;

        PokemonData mudkip = APIPetitions.getPokemonData("mudkip");
        DocumentExporter.exportToXml(mudkip,"G:\\Mi unidad\\DAM\\2_DAM_G\\AD\\PROYECTO API\\"+mudkip.getPokemon().getName());

    }
}
