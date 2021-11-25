package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stock;
    private Double value;
    private Double quantity;
    private Boolean national;

    public Stock() {

    }

    public Stock(String stock, Double value, Double quantity, Boolean national) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
        this.national = national;
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
}
