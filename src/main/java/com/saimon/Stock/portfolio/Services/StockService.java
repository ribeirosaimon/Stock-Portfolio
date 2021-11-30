package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.DTO.StockDTO;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockService {
    private Logger Log = LoggerFactory.getLogger(StockService.class);
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private StockEntity stockEntity;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private CashService cashService;
    @Autowired
    private Consumer apiConsumer;

    public void buyStock(Stock stock) {
        Double dolarValue = null;
        var cash = (stock.getValue() * stock.getQuantity());
        BalanceDTO cashBalance = balanceService.balance(stock.getNational());
        if (cashBalance.getBalance() >= cash) {
            if (!stock.getNational()) {
                dolarValue = apiConsumer.conDolarPrice().get().getAsk();
            }
            Cash buyStockCash = new Cash(cash * -1, stock.getNational(), dolarValue);
            cashEntity.save(buyStockCash);
            stockEntity.save(stock);
        }
    }

    public void sellStock(Stock stock) {
        Double dolarvalue = null;
        StockDTO infoStock = new StockDTO(stock.getStock(), 0D, 0D, stock.getNational());
        List<Stock> allStocks = stockEntity.findAll();
        for (Stock newStock : stockEntity.findAll()) {
            if (newStock.getBuy()) {
                infoStock.setQuantity(infoStock.getQuantity() + newStock.getQuantity());
            } else {
                infoStock.setQuantity(infoStock.getQuantity() - newStock.getQuantity());
            }
        }
        if (stock.getQuantity() <= infoStock.getQuantity()) {
            stockEntity.save(stock);
            if (!stock.getNational()) {
                dolarvalue = apiConsumer.conDolarPrice().get().getAsk();
            }
            cashService.deposit(stock.getValue() * stock.getQuantity(),
                    stock.getNational(),
                    dolarvalue);
        }
    }
}
