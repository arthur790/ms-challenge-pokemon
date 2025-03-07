
package com.pokemon.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {

    protected String name;
    protected String url;


}
