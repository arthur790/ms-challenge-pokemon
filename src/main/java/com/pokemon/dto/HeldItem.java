
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
public class HeldItem implements Serializable {

    private Item item;
    @JsonProperty(value="version_details")
    private List<VersionDetail> versionDetails;


}
