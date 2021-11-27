package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.DTO.StockDTO;
import com.saimon.Stock.portfolio.Database.Entity.BalanceEntity;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    private Logger Log = LoggerFactory.getLogger(BalanceService.class);
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private BalanceEntity balanceEntity;
    @Autowired
    private StockEntity stockEntity;

    public BalanceDTO scheduleBalance() {
        BalanceDTO usaBalance = balance(false);
        BalanceDTO brBalance = balance(true);
        var totalBalance = brBalance.getBalance() + usaBalance.getBalance();
        Balance balance = new Balance(usaBalance.getBalance(), brBalance.getBalance(), totalBalance);
        balanceEntity.save(balance);
        return new BalanceDTO(balance.getTotalBalance());
    }

    public BalanceDTO balance(Boolean national) {
        double balanceValue = 0D;
        if (national == null) {
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

    public StockDTO infoStock(String stock) {
        Double averageValue = 0D;
        Double quantity = 0D;

        for (Stock oneStock : stockEntity.findAll()) {
            if (stock.equals(oneStock.getStock())) {
                if (oneStock.getBuy()) {
                    quantity += oneStock.getQuantity();
                    averageValue += (oneStock.getQuantity() * oneStock.getValue());
                } else {
                    quantity -= oneStock.getQuantity();
                    averageValue -= (oneStock.getQuantity() * oneStock.getValue());
                }
            }
        }
        return new StockDTO(stock, (averageValue / quantity), quantity);
    }
}
