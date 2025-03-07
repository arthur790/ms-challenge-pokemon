package com.pokemon.client;


import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.PokemonItemDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "api-pokemon", url = "${client.url.pokemon}")
public interface FeignPokemonClient {

    @GetMapping("pokemon?limit={limit}&offset={offset}")
    ResponseEntity<List<PokemonItemDto>> getAllPokemonWithPagination(
            @Param long limit, @Param long offset
    );


    @GetMapping("pokemon/{name}/")
    ResponseEntity<PokemonDto> getPokemonByName(
            @Param String name
    );


}
