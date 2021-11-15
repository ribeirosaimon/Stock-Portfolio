package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DolarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double high;
    private Double low;
    private Long timestamp;

    public DolarModel() {

    }

    public DolarModel(Double high, Double low, Long timestamp) {
        this.high = high;
        this.low = low;
        this.timestamp = timestamp;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DolarModel)) return false;
        DolarModel that = (DolarModel) o;
        return Objects.equals(id, that.id) && Objects.equals(getHigh(), that.getHigh()) && Objects.equals(getLow(), that.getLow()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getHigh(), getLow(), getTimestamp());
    }
}
