package com.pokemon.controller;

import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDetailEvolutionDto;
import com.pokemon.service.PokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonService pokemonService;



    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Page<PokemonDetailDto>> getPokemonWithPagination(
            @RequestParam(required = false, defaultValue = "") Integer size,
            @RequestParam(required = false, defaultValue = "") Integer pageNumber) throws Exception{

        return new ResponseEntity<>(
                pokemonService.getPokemonPagination(size, pageNumber),
                HttpStatus.OK
        );
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<PokemonDetailEvolutionDto> getPokemonDetailAndEvolution(
            @PathVariable String name) throws Exception{

        return new ResponseEntity<>(
                pokemonService.getPokemonDetailAndEvolution(name),
                HttpStatus.OK
        );
    }





}
