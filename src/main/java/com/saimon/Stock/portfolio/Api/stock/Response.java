package com.saimon.Stock.portfolio.Api.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private final StockInfo stockInfoJson;


    public Response(@JsonProperty("chart") StockInfo stockJson) {
        this.stockInfoJson = stockJson;
    }

    public StockInfo getChartJson() {
        return this.stockInfoJson;
    }
}
