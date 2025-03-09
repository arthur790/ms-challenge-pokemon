package com.pokemon.client;

import com.pokemon.dto.PokemonPaginationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "api-pokemon", url = "${client.url.pokemon}")
public interface FeignPokemonClient {

    @RequestMapping("pokemon")
    ResponseEntity<PokemonPaginationDto> getAllPokemonWithPagination(
            @RequestParam(value = "limit") long limit, @RequestParam(value = "offset") long offset
    );





}
