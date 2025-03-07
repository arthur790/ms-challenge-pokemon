package com.pokemon.service;

import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDetailEvolutionDto;
import com.pokemon.dto.PokemonItemDto;

import java.util.List;

public interface PokemonService {

    List<PokemonDetailDto> getPokemonPagination(long limit, long offset) throws Exception;

    PokemonDetailEvolutionDto getPokemonDetailAndEvolution(String name) throws Exception;
}
