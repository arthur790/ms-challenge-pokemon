package com.pokemon.resttemplate;

import com.pokemon.dto.PokemonDto;

import com.pokemon.dto.PokemonSpeciesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class PokemonRestTemplateTest {


    @InjectMocks
    PokemonRestTemplateImpl pokemonRestTemplateImpl;



    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(pokemonRestTemplateImpl, "url", "https://pokeapi.co/api/v2/");
    }


    @Test
    public void testGetPokemonDetailFromName(){
        String name = "charmeleon";

        //when
        try {
            PokemonDto result = pokemonRestTemplateImpl.getPokemonDetailFromName(name);

            //then
            assertNotNull(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetPokemonSpecies(){
        String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/5/";

        try {
            PokemonSpeciesDto result = pokemonRestTemplateImpl.getPokemonSpecies(speciesUrl);

            //then
            assertNotNull(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testGetPokemonEvolutionFromSpecies(){
        String urlEvolutionFromSpecies = "https://pokeapi.co/api/v2/evolution-chain/2/";

        try {
            String result = pokemonRestTemplateImpl.getPokemonEvolutionFromSpecies(urlEvolutionFromSpecies);

            assertNotNull(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
