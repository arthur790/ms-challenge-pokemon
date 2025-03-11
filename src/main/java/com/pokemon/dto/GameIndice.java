
package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameIndice implements Serializable {
	
	@JsonProperty(value="game_index")
    private BigInteger gameIndex;
    private Version version;

}
