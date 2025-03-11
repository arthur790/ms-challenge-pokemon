package com.pokemon;

import com.pokemon.dto.*;

import java.math.BigInteger;
import java.util.List;

public class DataProvider {

    public static List<EvolutionDto> evolutionsListMock(){
        System.out.println(" -> Obteniendo listado de Evolutions / Mock");
        return List.of(
                new EvolutionDto("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png", "charizard", 6),
                new EvolutionDto("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png", "charmeleon", 5),
                new EvolutionDto("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png","charmander",4)

        );
    }

    public static PokemonSpeciesDto pokemonSpeciesMock(){
        return new PokemonSpeciesDto(new EvolutionChangeDto("https://pokeapi.co/api/v2/evolution-chain/2/"));
    }
    public static String evolututionsMock(){
        return "{\"baby_trigger_item\": null, \"chain\": {\"evolution_details\": [], \"evolves_to\": [{\"evolution_details\": [{\"gender\": null, \"held_item\": null, \"item\": null, \"known_move\": null, \"known_move_type\": null, \"location\": null, \"min_affection\": null, \"min_beauty\": null, \"min_happiness\": null, \"min_level\": 16, \"needs_overworld_rain\": false, \"party_species\": null, \"party_type\": null, \"relative_physical_stats\": null, \"time_of_day\": \"\", \"trade_species\": null, \"trigger\": {\"name\": \"level-up\", \"url\": \"https://pokeapi.co/api/v2/evolution-trigger/1/\" }, \"turn_upside_down\": false } ], \"evolves_to\": [{\"evolution_details\": [{\"gender\": null, \"held_item\": null, \"item\": null, \"known_move\": null, \"known_move_type\": null, \"location\": null, \"min_affection\": null, \"min_beauty\": null, \"min_happiness\": null, \"min_level\": 36, \"needs_overworld_rain\": false, \"party_species\": null, \"party_type\": null, \"relative_physical_stats\": null, \"time_of_day\": \"\", \"trade_species\": null, \"trigger\": {\"name\": \"level-up\", \"url\": \"https://pokeapi.co/api/v2/evolution-trigger/1/\" }, \"turn_upside_down\": false } ], \"evolves_to\": [], \"is_baby\": false, \"species\": {\"name\": \"charizard\", \"url\": \"https://pokeapi.co/api/v2/pokemon-species/6/\" } } ], \"is_baby\": false, \"species\": {\"name\": \"charmeleon\", \"url\": \"https://pokeapi.co/api/v2/pokemon-species/5/\" } } ], \"is_baby\": false, \"species\": {\"name\": \"charmander\", \"url\": \"https://pokeapi.co/api/v2/pokemon-species/4/\" } }, \"id\": 2 }";
    }
    public static PokemonDto pokemonMock(){
        return new PokemonDto(
                List.of(new Abilities( new Ability("blaze", "https://pokeapi.co/api/v2/ability/66/"), "false", 1)),
                new BigInteger("142"),
                List.of(new Form("charmeleon", "https://pokeapi.co/api/v2/pokemon-form/5/")),
                List.of(new GameIndice(new BigInteger("178"), new Version("red", "https://pokeapi.co/api/v2/version/1/"))),
                new BigInteger("11"),
                List.of(),
                5,
                "true",
                "https://pokeapi.co/api/v2/pokemon/5/encounters",
                List.of(),
                "charmeleon",
                new BigInteger("6"),
                "",
                new Species("charmeleon", "https://pokeapi.co/api/v2/pokemon-species/5/"),

                new BigInteger("190"),
                List.of(new Types(new BigInteger("1"), new Type("fire","https://pokeapi.co/api/v2/type/10/" ))),
                new SpriteDto("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/5.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/5.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/5.png",null, null)


        );
    }
}
