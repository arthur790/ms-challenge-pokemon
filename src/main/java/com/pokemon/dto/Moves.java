
package com.pokemon.dto;


import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moves implements Serializable {

    protected Move move;
    @JsonProperty(value="version_group_details")
    protected List<VersionGroupDetail> versionGroupDetails;


}
