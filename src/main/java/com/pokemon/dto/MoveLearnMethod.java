
package com.pokemon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveLearnMethod implements Serializable {

    protected String name;
    protected String url;

}
