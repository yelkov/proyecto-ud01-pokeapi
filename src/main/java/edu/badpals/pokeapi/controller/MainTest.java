package edu.badpals.pokeapi.controller;

import edu.badpals.pokeapi.model.Area;
import edu.badpals.pokeapi.model.Pokemon;
import edu.badpals.pokeapi.service.APIPetitions;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        Pokemon poke = APIPetitions.askAPIforPokemon("bulbasaur");
        System.out.println(poke);
        List<Area> areas = APIPetitions.askAPIforArea(poke.getId());
        System.out.println(areas);
    }
}
