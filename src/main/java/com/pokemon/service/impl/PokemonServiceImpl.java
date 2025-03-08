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

     final private PokemonRestTemplate pokemonRestTemplate;
     final private FeignPokemonClient feignPokemonClient;


    public PokemonServiceImpl(
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

        pokemonDetailEvolutionDto.setEvolutionDtoList(
                getPokemonDetailAndEvolutions(pokemonDto.getSpecies().getUrl())
        );

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
    private List<EvolutionDto> getPokemonDetailAndEvolutions(String urlSpecies) throws Exception {

        PokemonSpeciesDto pokemonSpeciesDto = pokemonRestTemplate.getPokemonSpecies(urlSpecies);


        String evolutions = pokemonRestTemplate.getPokemonEvolutionFromSpecies(pokemonSpeciesDto.getEvolutionChangeDto().getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(evolutions);

        List<JsonNode> jsonNodeList = jsonNode.findValues("species");
        System.out.println(jsonNodeList.toString());
        return jsonNodeList.stream().map( e -> {
            EvolutionDto item = new EvolutionDto();
            item.setName(e.get("name").asText());

            //from name call detail and get id and get sprites.back_default (image)
            try {
                PokemonDto pokemonDto = pokemonRestTemplate.getPokemonDetailFromName(item.getName());
                item.setId( pokemonDto.getId());
                item.setUrlImage( pokemonDto.getSprites().getFrontDefault());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            return item;
        }).toList();


    }
    private String getImageFromUrlEvolution(String urlEvolution) throws Exception{
        //example https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/5.png
        return null;
    }


}
