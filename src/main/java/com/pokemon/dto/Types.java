
package com.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Types implements Serializable {


    protected BigInteger slot;

    protected Type type;



}
