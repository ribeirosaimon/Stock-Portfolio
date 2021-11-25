package com.saimon.Stock.portfolio.Api.dolar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private final DolarPrice dolar;

    public Response(@JsonProperty("USDBRL") DolarPrice dolarJson) {
        this.dolar = dolarJson;
    }

    public DolarPrice getDolar() {
        return this.dolar;
    }
}
