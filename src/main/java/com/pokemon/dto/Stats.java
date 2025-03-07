package com.pokemon.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class Stats implements Serializable {


    protected BigInteger baseStat;
    protected BigInteger effort;
    protected Stat stat;



}
