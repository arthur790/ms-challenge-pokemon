
package com.pokemon.dto;


import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class HeldItem implements Serializable {

    protected Item item;
    @JsonProperty(value="version_details")
    protected List<VersionDetail> versionDetails;


}
