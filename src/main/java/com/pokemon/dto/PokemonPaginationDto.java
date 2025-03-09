package com.pokemon.dto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PokemonPaginationDto implements Serializable {

    private Integer count;
    private String next;

    private String previous;
    private List<PokemonItemDto> results;

}
