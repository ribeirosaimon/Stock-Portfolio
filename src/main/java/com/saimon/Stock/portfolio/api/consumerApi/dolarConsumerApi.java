package com.saimon.Stock.portfolio.api.consumerApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saimon.Stock.portfolio.api.jsonObj.dolarPrice;

public class dolarConsumerApi {
    private final dolarPrice dolar;

    public dolarConsumerApi(@JsonProperty("USDBRL") dolarPrice dolar) {
        this.dolar = dolar;
    }

    public dolarPrice getDolar(){
        return this.dolar;
    }
}
