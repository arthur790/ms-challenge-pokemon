package com.pokemon.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.config.CacheConfig;
import com.pokemon.dto.EvolutionDto;
import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.PokemonSpeciesDto;
import com.pokemon.resttemplate.PokemonRestTemplate;
import com.pokemon.service.PokemonApiService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonApiServiceImpl implements PokemonApiService {

    final private PokemonRestTemplate pokemonRestTemplate;

    public PokemonApiServiceImpl( PokemonRestTemplate pokemonRestTemplate){
        this.pokemonRestTemplate = pokemonRestTemplate;
    }


    @Override
    @Cacheable(value = CacheConfig.POKEMON_EVOLUTION_DATA_CACHE, unless="#result == null")
    public List<EvolutionDto> getPokemonDetailAndEvolutions(String urlSpecies) throws Exception {

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

    @Override
    @Cacheable(value = CacheConfig.POKEMON_DETAIL_DATA_CACHE, unless="#result == null")
    public PokemonDetailDto getPokemonDetail(String url) throws Exception {
        PokemonDetailDto pokemonDetailDto = new PokemonDetailDto();

        PokemonDto pokemonDto = pokemonRestTemplate.getPokemonDetail(url);
        pokemonDetailDto.setName(pokemonDto.getName());
        pokemonDetailDto.setAbilities(pokemonDto.getAbilities());
        pokemonDetailDto.setWeight(pokemonDto.getWeight());
        pokemonDetailDto.setTypes(pokemonDto.getTypes());
        pokemonDetailDto.setUrlImage( pokemonDto.getSprites().getFrontDefault());

        return pokemonDetailDto;
    }

    @Override
    @Cacheable(value = CacheConfig.POKEMON_DETAIL_NAME_DATA_CACHE, unless="#result == null")
    public PokemonDto getPokemonDetailFromName(String name) throws Exception {
        return pokemonRestTemplate.getPokemonDetailFromName(name);
    }
}
