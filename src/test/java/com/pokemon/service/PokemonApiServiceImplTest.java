package com.pokemon.service;


import com.pokemon.DataProvider;
import com.pokemon.dto.EvolutionDto;
import com.pokemon.dto.PokemonDetailDto;
import com.pokemon.dto.PokemonDto;
import com.pokemon.resttemplate.PokemonRestTemplate;
import com.pokemon.service.impl.PokemonApiServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonApiServiceImplTest {

    @Mock
    private PokemonRestTemplate pokemonRestTemplate;

    @InjectMocks
    private PokemonApiServiceImpl pokemonApiService;


    @Test
    public void testGetPokemonDetailAndEvolutions(){

        String urlSpecies = "https://pokeapi.co/api/v2/pokemon-species/5/";


        //When
        try {
            when(pokemonRestTemplate.getPokemonSpecies(anyString())).thenReturn(DataProvider.pokemonSpeciesMock());
            when(pokemonRestTemplate.getPokemonEvolutionFromSpecies(anyString())).thenReturn(DataProvider.evolututionsMock());
            when(pokemonRestTemplate.getPokemonDetailFromName(anyString())).thenReturn(DataProvider.pokemonMock());

            List<EvolutionDto> result = pokemonApiService.getPokemonDetailAndEvolutions(urlSpecies);

            //then
            assertNotNull(result);
            assertFalse(result.isEmpty());
            verify(this.pokemonRestTemplate).getPokemonSpecies(urlSpecies);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetPokemonDetail(){
        String url = "https://pokeapi.co/api/v2/pokemon/4/";

        //when
        try {
            when(pokemonRestTemplate.getPokemonDetail(url)).thenReturn(DataProvider.pokemonMock());
            PokemonDetailDto result = pokemonApiService.getPokemonDetail(url);

            //then
            assertNotNull(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testGetPokemonDetailFromName(){
        String name = "charmeleon";

        try {
            //when
            when(pokemonRestTemplate.getPokemonDetailFromName(anyString())).thenReturn(DataProvider.pokemonMock());
            PokemonDto result = pokemonApiService.getPokemonDetailFromName(name);

            //then
            assertNotNull(result);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
