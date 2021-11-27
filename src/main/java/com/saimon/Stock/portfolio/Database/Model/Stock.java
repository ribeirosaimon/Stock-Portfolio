package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stock;
    private Double value;
    private Double quantity;
    private Boolean national;
    private Boolean buy;

    public Stock() {

    }

    public Stock(String stock, Double value, Double quantity, Boolean national, Boolean buy) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
        this.national = national;
        this.buy = buy;
    }

    public String getStock() {
        return stock;
    }

    public Double getValue() {
        return value;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Boolean getNational() {
        return national;
    }

    public Boolean getBuy() {
        return buy;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock1 = (Stock) o;
        return Objects.equals(id, stock1.id) && Objects.equals(getStock(), stock1.getStock()) && Objects.equals(getValue(), stock1.getValue()) && Objects.equals(getQuantity(), stock1.getQuantity()) && Objects.equals(getNational(), stock1.getNational()) && Objects.equals(getBuy(), stock1.getBuy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getStock(), getValue(), getQuantity(), getNational(), getBuy());
    }
}
