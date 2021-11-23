package com.saimon.Stock.portfolio.services;

import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashService {
    private Logger Log = LoggerFactory.getLogger(CashService.class);
    @Autowired
    private CashEntity cashEntity;

    public Cash deposit(Double value, Boolean national, Double dolar) {
        if (!national) {
            if (dolar != null) {
                Cash newCash = new Cash(value, false, dolar);
                cashEntity.save(newCash);
                return newCash;
            }
            throw new NullPointerException("Dolar don't can null");
        } else if (value != null) {
            Cash newCash = new Cash(value, true, dolar);
            cashEntity.save(newCash);
            return newCash;
        } else {
            throw new NullPointerException("Dolar don't can null");
        }
    }

    public void withdraw(Double value, Boolean national, Double dolar) {
        if (balance(national) <= value) {
            throw new IllegalStateException("you don't have cash");
        }
        if (!national) {
            if (dolar != null) {
                cashEntity.save(new Cash(-value, false, dolar));
            }
            throw new NullPointerException("Dolar don't can null");
        } else if (value != null) {
            cashEntity.save(new Cash(-value, true, dolar));
        }
    }

    public Double balance(Boolean national) {
        double balanceValue = 0D;
        for (Cash cash : cashEntity.findAll()) {
            if (cash.getNational() == national) {
                double cashValue = cash.getCashValue();
                balanceValue += cashValue;
            }
        }
        return balanceValue;
    }
}
