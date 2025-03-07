
package com.pokemon.dto;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VersionGroupDetail implements Serializable {
	
	@JsonProperty(value="level_learned_at")
    protected BigInteger levelLearnedAt;
	@JsonProperty(value="move_learn_method")
    protected MoveLearnMethod moveLearnMethod;
	@JsonProperty(value="version_group")
    protected Version versionGroup;

}
