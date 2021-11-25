package com.saimon.Stock.portfolio.Services;

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
    @Autowired
    private BalanceService balanceService;

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

    public Cash withdraw(Double value, Boolean national, Double dolar) {
        if (balanceService.balance(national).getBalance() <= value) {
            throw new IllegalStateException("you don't have cash");
        }
        if (!national) {
            if (dolar != null) {
                Cash cashValue = new Cash(-value, false, dolar);
                cashEntity.save(cashValue);
                return cashValue;
            }
            throw new NullPointerException("Dolar don't can null");
        } else if (value != null) {
            Cash cashValue = new Cash(-value, true, dolar);
            cashEntity.save(cashValue);
            return cashValue;
        } else {
            throw new NullPointerException("Dolar don't can null");
        }
    }


}
