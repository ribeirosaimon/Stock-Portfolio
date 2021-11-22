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

    public Stock(){

    }

    public Stock(String stock, Double value, Double quantity) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
    }

}
