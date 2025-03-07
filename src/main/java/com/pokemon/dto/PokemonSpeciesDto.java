package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class PokemonSpeciesDto  implements Serializable {

    @JsonProperty(value = "evolution_chain")
    private EvolutionChangeDto evolutionChangeDto;

}
