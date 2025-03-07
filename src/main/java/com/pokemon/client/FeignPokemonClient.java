package com.pokemon.client;


import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.PokemonItemDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "api-pokemon", url = "${client.url.pokemon}")
public interface FeignPokemonClient {

    @RequestMapping("pokemon")
    ResponseEntity<List<PokemonItemDto>> getAllPokemonWithPagination(
            @RequestParam(value = "limit") long limit, @RequestParam(value = "offset") long offset
    );


    @GetMapping("pokemon/{name}/")
    ResponseEntity<PokemonDto> getPokemonByName(
            @Param String name
    );


}
