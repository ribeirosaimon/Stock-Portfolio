package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

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

    public Double getCashValue() {
        return cashValue;
    }

    public Boolean getNational() {
        return national;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cash)) return false;
        Cash cash = (Cash) o;
        return Objects.equals(id, cash.id) && Objects.equals(getCashValue(), cash.getCashValue()) && Objects.equals(getNational(), cash.getNational()) && Objects.equals(dolar, cash.dolar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCashValue(), getNational(), dolar);
    }
}
