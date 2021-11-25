package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.Controllers.CashController;
import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockService {
    private Logger Log = LoggerFactory.getLogger(StockService.class);
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private Consumer apiConsumer;

    public void buyStock(Stock stock) {
        Double dolarValue = null;
        var cash = (stock.getValue() * stock.getQuantity());
        BalanceDTO cashBalance = balanceService.balance(stock.getNational());
        Log.info("Aqui");
        Log.info(cashBalance.getBalance().toString());
        Log.info(String.valueOf(cash));
        if (cashBalance.getBalance() >= cash) {
            Log.info("PASSOU AQUI");
            if (!stock.getNational()) {
                dolarValue = apiConsumer.conDolarPrice().get().getAsk();
            }
            Cash buyStockCash = new Cash(cash * -1, stock.getNational(), dolarValue);
            cashEntity.save(buyStockCash);
        }
    }
}
