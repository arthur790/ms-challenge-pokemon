package com.pokemon.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class Species implements Serializable {

    protected String name;
    protected String url;

}
