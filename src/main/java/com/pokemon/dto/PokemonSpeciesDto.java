package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonSpeciesDto  implements Serializable {

    @JsonProperty(value = "evolution_chain")
    private EvolutionChangeDto evolutionChangeDto;

}
