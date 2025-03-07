package com.pokemon.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PokemonDetailEvolutionDto extends PokemonDetailDto implements Serializable {

    private List<EvolutionDto> evolutionDtoList;

}
