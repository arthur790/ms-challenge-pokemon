package com.pokemon.service;

import com.pokemon.DataProvider;
import com.pokemon.dto.PokemonDetailEvolutionDto;
import com.pokemon.dto.PokemonDto;
import com.pokemon.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceImplTest {

    @Mock
    PokemonApiService pokemonApiService;

    @InjectMocks
    PokemonServiceImpl pokemonService;

    @Test
    public void testGetPokemonDetailAndEvolution(){
        String name = "charmeleon";

        String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/5/";

        try {
            when(pokemonApiService.getPokemonDetailFromName(name)).thenReturn(DataProvider.pokemonMock());

            PokemonDetailEvolutionDto result = pokemonService.getPokemonDetailAndEvolution(name);

            //then
            assertNotNull(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
