package com.pokemon.resttemplate;


import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.PokemonSpeciesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonRestTemplateImpl implements PokemonRestTemplate{

    @Value("${client.url.pokemon}")
    private String url;

    @Override
    public PokemonDto getPokemonDetailFromName(String name) throws Exception {

        return getPokemonDetail(url + "/pokemon/"+ name + "/");
    }

    @Override
    public PokemonDto getPokemonDetail(String url) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PokemonDto> response
                = restTemplate.getForEntity(url, PokemonDto.class);

        return response.getBody();
    }

    @Override
    public PokemonSpeciesDto getPokemonSpecies(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PokemonSpeciesDto> response
                = restTemplate.getForEntity(url, PokemonSpeciesDto.class);

        return response.getBody();
    }

    @Override
    public String getPokemonEvolutionFromSpecies(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }
}
