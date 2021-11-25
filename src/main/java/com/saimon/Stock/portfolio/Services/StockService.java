package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockService {

    @Autowired
    private BalanceService balanceService;

    public void buyStock(Stock stock) {
        double cash;
        if (stock.getNational()){
            cash = stock.getValue() * stock.getQuantity();
        } else {
            cash = (stock.getValue() * stock.getQuantity());
        }

        COMPRAR ACAO

        balanceService.balance(stock.getNational());
    }
}
