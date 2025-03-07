
package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class GameIndice implements Serializable {
	
	@JsonProperty(value="game_index")
    protected BigInteger gameIndex;
    protected Version version;

}
