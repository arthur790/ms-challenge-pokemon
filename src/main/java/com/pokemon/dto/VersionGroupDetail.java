
package com.pokemon.dto;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionGroupDetail implements Serializable {
	
	@JsonProperty(value="level_learned_at")
    private BigInteger levelLearnedAt;
	@JsonProperty(value="move_learn_method")
    private MoveLearnMethod moveLearnMethod;
	@JsonProperty(value="version_group")
    private Version versionGroup;

}
