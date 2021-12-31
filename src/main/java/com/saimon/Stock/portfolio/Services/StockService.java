package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.Database.Repository.CashRepository;
import com.saimon.Stock.portfolio.Database.Repository.StockRepository;
import com.saimon.Stock.portfolio.Exception.NoMoneyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class StockService {
    private Logger Log = LoggerFactory.getLogger(StockService.class);
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private Consumer apiConsumer;

    @Transactional
    public void buyStock(Stock stock) {
        Double dolarValue = null;
        var cash = (stock.getValue() * stock.getQuantity());
        BalanceDTO cashBalance = balanceService.balance(stock.getNational());
        if (cashBalance.getBalance() >= cash) {
            if (!stock.getNational()) {
                dolarValue = apiConsumer.conDolarPrice().get().getAsk();
            }
            Cash buyStockCash = new Cash(cash * -1, stock.getNational(), dolarValue);
            cashRepository.save(buyStockCash);
            stockRepository.save(stock);
        } else {
            throw new NoMoneyException();
        }
    }

    @Transactional
    public void sellStock(Stock stock) {
        Double dolarvalue = null;
        List<Stock> allStocks = stockRepository.findAll();
        if (balanceService.infoStock(stock.getStock()).getQuantity() >= stock.getQuantity()) {
            stockRepository.save(stock);
        }
    }
}
