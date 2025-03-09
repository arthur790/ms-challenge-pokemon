package com.pokemon.service;

import com.pokemon.dto.EvolutionDto;
import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDto;

import java.util.List;

public interface PokemonApiService {
    List<EvolutionDto> getPokemonDetailAndEvolutions(String urlSpecies) throws Exception;
    PokemonDetailDto getPokemonDetail(String url) throws Exception;
    PokemonDto getPokemonDetailFromName(String name) throws Exception;
}
