
package com.pokemon.dto;

import java.io.Serializable;
import java.math.BigInteger;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Abilities implements Serializable {

    protected Ability ability;
    @JsonProperty(value="is_hidden")
    protected String isHidden;

    protected BigInteger slot;

}
