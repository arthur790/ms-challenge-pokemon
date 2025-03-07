package com.pokemon.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EvolutionDto implements Serializable {
    private String urlImage;
    private String name;
    private Integer id;
}
