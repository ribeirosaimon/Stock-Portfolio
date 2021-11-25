package com.saimon.Stock.portfolio.Api.stock;

import java.util.Objects;

public class StockPrice {
    private final String symbol;
    private final double low;
    private final double open;
    private final double close;
    private final double high;
    private final long volume;

    public StockPrice(String symbol, double low, double open, double close, double high, long volume) {
        this.symbol = symbol;
        this.low = low;
        this.open = open;
        this.close = close;
        this.high = high;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockPrice)) return false;
        StockPrice that = (StockPrice) o;
        return Double.compare(that.getLow(), getLow()) == 0 && Double.compare(that.getOpen(), getOpen()) == 0 && Double.compare(that.getClose(), getClose()) == 0 && Double.compare(that.getHigh(), getHigh()) == 0 && getVolume() == that.getVolume() && Objects.equals(getSymbol(), that.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getLow(), getOpen(), getClose(), getHigh(), getVolume());
    }
}
