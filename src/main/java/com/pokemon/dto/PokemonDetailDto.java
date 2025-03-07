package com.pokemon.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
public class PokemonDetailDto implements Serializable {
    private String name;
    private BigInteger weight;
    private List<Types> types;
    private List<Abilities> abilities;

}
