package com.pokemon.service;

import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDetailEvolutionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PokemonService {

    Page<PokemonDetailDto> getPokemonPagination(Integer size, Integer pageNumber) throws Exception;

    PokemonDetailEvolutionDto getPokemonDetailAndEvolution(String name) throws Exception;
}
