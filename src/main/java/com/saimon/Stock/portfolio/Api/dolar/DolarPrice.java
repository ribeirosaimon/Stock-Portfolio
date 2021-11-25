package com.saimon.Stock.portfolio.Api.dolar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DolarPrice {

    private final double high;
    private final double low;
    private final double ask;
    private final double bid;
    private final long timestamp;


    public DolarPrice(@JsonProperty("high") double high,
                      @JsonProperty("low") double low,
                      @JsonProperty("ask") double ask,
                      @JsonProperty("bid") double bid,
                      @JsonProperty("timestamp") long timestamp) {
        this.high = high;
        this.low = low;
        this.ask = ask;
        this.bid = bid;
        this.timestamp = timestamp;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public long getTimestamp() {
        return timestamp;
    }


}
