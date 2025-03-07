package com.pokemon.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonDto implements Serializable {
	
	
	private List<Abilities> abilities;

	@JsonProperty(value="base_experience")
	private BigInteger baseExperience;
	
	private List<Form> forms;
	
	@JsonProperty(value="game_indices")
	private List<GameIndice> gameIndices;
	
	private BigInteger height;
	
	@JsonProperty(value="held_items")
	private List<HeldItem> heldItems;

	private BigInteger id;
	
	@JsonProperty(value="is_default")
	private String isDefault;
	
	@JsonProperty(value="location_area_encounters")
	private String locationAreaEncounters;
	
	private List<Moves> moves;
	
	private String name;
	
	private BigInteger order;
	
	private String pastTypes;
	
	private Species species;
	
	private BigInteger weight;

	private List<Types> types;

	
	
	


}
