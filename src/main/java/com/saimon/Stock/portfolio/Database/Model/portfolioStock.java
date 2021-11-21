package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class portfolioStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stock;
    private Double value;
    private Long quantity;

    public portfolioStock(){

    }

    public portfolioStock(String stock, Double value, Long quantity) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
    }

}
