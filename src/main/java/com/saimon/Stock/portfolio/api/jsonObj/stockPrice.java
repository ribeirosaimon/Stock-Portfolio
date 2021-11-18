package com.saimon.Stock.portfolio.api.jsonObj;

public class stockPrice {
    private final double high;
    private final double low;
    private final double open;
    private final double close;
    private final long volume;

    public stockPrice(double high, double low, double open, double close, long volume) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.volume = volume;
    }
}
