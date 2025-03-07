package com.pokemon.controller;

import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDetailEvolutionDto;
import com.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonService pokemonService;



    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<PokemonDetailDto>> getPokemonWithPagination(
            @RequestParam(required = false, defaultValue = "") long limit,
            @RequestParam(required = false, defaultValue = "") long offset) throws Exception{

        return new ResponseEntity<>(
                pokemonService.getPokemonPagination(limit, offset),
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
