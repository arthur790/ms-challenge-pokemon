
package com.pokemon.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;


@Data
public class Types implements Serializable {


    protected BigInteger slot;

    protected Type type;



}
