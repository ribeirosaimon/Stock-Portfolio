package com.saimon.Stock.portfolio.api.consumerApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saimon.Stock.portfolio.api.jsonObj.stockPrice;

public class stockConsumerApi {
    private final stockInfo stockInfoJson;


    public stockConsumerApi(@JsonProperty("chart") stockInfo stockJson) {
        this.stockInfoJson = stockJson;
    }

    public stockInfo getChartJson() {
        return this.stockInfoJson;
    }
}
