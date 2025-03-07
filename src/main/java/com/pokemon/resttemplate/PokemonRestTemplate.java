package com.pokemon.resttemplate;


import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.PokemonSpeciesDto;

public interface PokemonRestTemplate {

    PokemonDto getPokemonDetailFromName(String name) throws Exception;
    PokemonDto getPokemonDetail(String url) throws Exception;

    PokemonSpeciesDto getPokemonSpecies(String url) throws Exception;

    String getPokemonEvolutionFromSpecies(String url) throws Exception;
}
