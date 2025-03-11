package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpriteDto implements Serializable {

    @JsonProperty(value = "back_default")
    private String backDefault;
    @JsonProperty(value = "back_shiny")
    private String backShiny;
    @JsonProperty(value = "front_default")
    private String frontDefault;
    @JsonProperty(value = "front_shiny")
    private String frontShiny;
    private Object other;
    private Object versions;

}
