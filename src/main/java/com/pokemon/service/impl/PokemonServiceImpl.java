package com.pokemon.service.impl;


import com.pokemon.client.FeignPokemonClient;
import com.pokemon.config.CacheConfig;
import com.pokemon.dto.*;
import com.pokemon.resttemplate.PokemonRestTemplate;
import com.pokemon.service.PokemonApiService;
import com.pokemon.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {


     final private FeignPokemonClient feignPokemonClient;
     final private PokemonApiService pokemonApiService;
     Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);


    public PokemonServiceImpl(
            FeignPokemonClient feignPokemonClient,
            PokemonApiService pokemonApiService
            ){

        this.feignPokemonClient = feignPokemonClient;
        this.pokemonApiService = pokemonApiService;

    }

    @Override
    public Page<PokemonDetailDto> getPokemonPagination(Integer size, Integer pageNumber) throws Exception {

        long limit = 100;
        Pageable pageable = PageRequest.of(pageNumber,  size);

        PokemonPaginationDto pokemonPaginationDto = feignPokemonClient.getAllPokemonWithPagination(
                limit, (int) pageable.getOffset()).getBody();


        if(pokemonPaginationDto.getResults().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Favor de revisar el número de página que desea consultar"
                    );
        }



        List<PokemonDetailDto> pokemonDetailDtoList =
                pokemonPaginationDto.getResults().stream().map((e) ->
                {
                    try {
                        return pokemonApiService.getPokemonDetail(e.getUrl());
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }).toList();



        return new PageImpl<>(pokemonDetailDtoList, pageable, pokemonPaginationDto.getCount());


    }

    @Override
    public PokemonDetailEvolutionDto getPokemonDetailAndEvolution(String name) throws Exception {
        PokemonDetailEvolutionDto pokemonDetailEvolutionDto = new PokemonDetailEvolutionDto();
        PokemonDto pokemonDto = pokemonApiService.getPokemonDetailFromName(name);

        pokemonDetailEvolutionDto.setAbilities(pokemonDto.getAbilities());
        pokemonDetailEvolutionDto.setWeight(pokemonDto.getWeight());
        pokemonDetailEvolutionDto.setTypes(pokemonDto.getTypes());
        pokemonDetailEvolutionDto.setName(pokemonDto.getName());
        pokemonDetailEvolutionDto.setUrlImage(pokemonDto.getSprites().getFrontDefault());

        pokemonDetailEvolutionDto.setEvolutionDtoList(
                pokemonApiService.getPokemonDetailAndEvolutions(pokemonDto.getSpecies().getUrl())
        );

        return pokemonDetailEvolutionDto;
    }




}
