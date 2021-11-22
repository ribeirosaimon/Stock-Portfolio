package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cashValue;
    private Boolean national;
    private Double dolar;

    public Cash() {

    }

    public Cash(Double cashValue, Boolean national, Double dolar) {
        this.cashValue = cashValue;
        this.national = national;
        this.dolar = dolar;
    }
}
