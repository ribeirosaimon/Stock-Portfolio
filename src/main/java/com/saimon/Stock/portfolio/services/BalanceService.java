package com.saimon.Stock.portfolio.services;

import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    private Logger Log = LoggerFactory.getLogger(BalanceService.class);
    @Autowired
    private CashEntity cashEntity;

    public BalanceDTO balance(Boolean national) {
        double balanceValue = 0D;
        if (national == null) {
            Log.info("OK");
            for (Cash cash : cashEntity.findAll()) {
                double cashValue = cash.getCashValue();
                balanceValue += cashValue;
            }
        } else {
            for (Cash cash : cashEntity.findAll()) {
                if (cash.getNational() == national) {
                    double cashValue = cash.getCashValue();
                    balanceValue += cashValue;
                }
            }
        }
        return new BalanceDTO(balanceValue);
    }
}
