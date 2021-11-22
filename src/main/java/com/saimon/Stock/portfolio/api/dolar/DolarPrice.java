package com.saimon.Stock.portfolio.api.dolar;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DolarPrice {

    private final double high;
    private final double low;
    private final long timestamp;


    public DolarPrice(@JsonProperty("high") double high,
                      @JsonProperty("low") double low,
                      @JsonProperty("timestamp") long timestamp) {
        this.high = high;
        this.low = low;
        this.timestamp = timestamp;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DolarPrice)) return false;
        DolarPrice that = (DolarPrice) o;
        return Double.compare(that.getHigh(), getHigh()) == 0 && Double.compare(that.getLow(), getLow()) == 0 && getTimestamp() == that.getTimestamp();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHigh(), getLow(), getTimestamp());
    }
}
