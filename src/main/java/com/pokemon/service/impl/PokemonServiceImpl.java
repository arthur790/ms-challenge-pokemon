package com.pokemon.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.client.FeignPokemonClient;
import com.pokemon.dto.*;
import com.pokemon.resttemplate.PokemonRestTemplate;
import com.pokemon.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRestTemplate pokemonRestTemplate;
    private final FeignPokemonClient feignPokemonClient;


    PokemonServiceImpl(
            PokemonRestTemplate pokemonRestTemplate,
            FeignPokemonClient feignPokemonClient
            ){
        this.pokemonRestTemplate = pokemonRestTemplate;
        this.feignPokemonClient = feignPokemonClient;

    }

    @Override
    public List<PokemonDetailDto> getPokemonPagination(long limit, long offset) throws Exception {

        List<PokemonItemDto> pokemonList = feignPokemonClient.getAllPokemonWithPagination(limit, offset).getBody();

        assert pokemonList != null;
        List<PokemonDetailDto> pokemonDetailDtoList =
            pokemonList.stream().map((e) -> getPokemonDetail(e.getUrl())).toList();

        return pokemonDetailDtoList;
    }

    @Override
    public PokemonDetailEvolutionDto getPokemonDetailAndEvolution(String name) throws Exception {
        PokemonDetailEvolutionDto pokemonDetailEvolutionDto = new PokemonDetailEvolutionDto();
        PokemonDto pokemonDto = pokemonRestTemplate.getPokemonDetailFromName(name);

        pokemonDetailEvolutionDto.setAbilities(pokemonDto.getAbilities());
        pokemonDetailEvolutionDto.setWeight(pokemonDto.getWeight());
        pokemonDetailEvolutionDto.setTypes(pokemonDto.getTypes());
        pokemonDetailEvolutionDto.setName(pokemonDto.getName());

        getPokemonDetailAndEvolutions(pokemonDto.getSpecies().getUrl());

        return pokemonDetailEvolutionDto;
    }

    private PokemonDetailDto getPokemonDetail(String url) {
        try {
            PokemonDetailDto pokemonDetailDto = new PokemonDetailDto();

            PokemonDto pokemonDto = pokemonRestTemplate.getPokemonDetail(url);
            pokemonDetailDto.setName(pokemonDto.getName());
            pokemonDetailDto.setAbilities(pokemonDto.getAbilities());
            pokemonDetailDto.setWeight(pokemonDto.getWeight());
            pokemonDetailDto.setTypes(pokemonDto.getTypes());

            return pokemonDetailDto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
    private void getPokemonDetailAndEvolutions(String urlSpecies) throws Exception {

        PokemonSpeciesDto pokemonSpeciesDto = pokemonRestTemplate.getPokemonSpecies(urlSpecies);


        String evolutions = pokemonRestTemplate.getPokemonEvolutionFromSpecies(pokemonSpeciesDto.getEvolutionChangeDto().getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(evolutions);
        
        List<JsonNode> jsonNodeList = jsonNode.findValues("species");

    }


}
