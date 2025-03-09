package com.pokemon.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.client.FeignPokemonClient;
import com.pokemon.config.CacheConfig;
import com.pokemon.dto.*;
import com.pokemon.resttemplate.PokemonRestTemplate;
import com.pokemon.service.PokemonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
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
    @Cacheable(value = CacheConfig.POKEMON_DATA_CACHE, unless="#result == null")
    public Page<PokemonDetailDto> getPokemonPagination(Integer size, Integer pageNumber) throws Exception {

        long limit = 100;
        long offset = 0;
        Pageable pageable = PageRequest.of(pageNumber,  size);
        final int start = (int) pageable.getOffset();

        PokemonPaginationDto pokemonPaginationDto = feignPokemonClient.getAllPokemonWithPagination(limit, (int) pageable.getOffset()).getBody();


        assert pokemonPaginationDto.getResults() != null;


        final int end = Math.min((start + pageable.getPageSize()), pokemonPaginationDto.getResults().size());

        List<PokemonDetailDto> pokemonDetailDtoList =
                pokemonPaginationDto.getResults().stream().map((e) -> getPokemonDetail(e.getUrl())).toList();



        return new PageImpl<>(pokemonDetailDtoList, pageable, pokemonPaginationDto.getCount());


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

    /**
     * Este metodo deberia manejar cache
     * @param url
     * @return
     */
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

            //from name call detail and get id and get sprites.front_default (image)
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



}
