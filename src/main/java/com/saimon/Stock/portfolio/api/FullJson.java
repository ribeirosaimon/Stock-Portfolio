package com.saimon.Stock.portfolio.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullJson {
    private final DolarPrice dolar;

    public FullJson(@JsonProperty("USDBRL") DolarPrice dolar) {
        this.dolar = dolar;
    }

    public DolarPrice getDolar(){
        return this.dolar;
    }
}
