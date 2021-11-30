package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.Api.stock.StockPrice;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {
    private Logger Log = LoggerFactory.getLogger(BalanceService.class);
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private BalanceEntity balanceEntity;
    @Autowired
    private StockEntity stockEntity;
    @Autowired
    private Consumer cons;

    public BalanceDTO scheduleBalance() {
        BalanceDTO usaBalance = balance(false);
        BalanceDTO brBalance = balance(true);
        BalanceDTO brStock = balanceCashStockPortfolio(true);
        BalanceDTO usaStock = balanceCashStockPortfolio(false);
        Double totalBalance = usaBalance.getBalance() + brBalance.getBalance() + usaStock.getBalance() + brStock.getBalance();
        Balance balance = new Balance(usaBalance.getBalance(), brBalance.getBalance(), usaStock.getBalance(), brStock.getBalance(), totalBalance);
        balanceEntity.save(balance);
        return new BalanceDTO(balance.getTotalBalance());
    }

    public BalanceDTO balance(Boolean national) {
        double balanceValue = 0D;
        if (national == null) {
            for (Cash cash : cashEntity.findAll()) {
                double cashValue = cash.getCashValue();
                if (!cash.getNational()) {
                    cashValue *= cons.conDolarPrice().get().getAsk();
                }
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
        Boolean national = false;

        for (Stock oneStock : stockEntity.findAll()) {
            if (stock.equals(oneStock.getStock())) {
                national = oneStock.getNational();
                if (oneStock.getBuy()) {
                    quantity += oneStock.getQuantity();
                    averageValue += (oneStock.getQuantity() * oneStock.getValue());
                } else {
                    quantity -= oneStock.getQuantity();
                    averageValue -= (oneStock.getQuantity() * oneStock.getValue());
                }
            }
        }
        return new StockDTO(stock, (averageValue / quantity), quantity, national);
    }

    public BalanceDTO balanceCashStockPortfolio(Boolean national) {
        Double valueStockCash = 0D;
        for (StockDTO stock : minePortfolio()) {
            if (stock.getNational() == national) {
                valueStockCash += cons.conStockPrice(stock.getStock()).getClose() * stock.getQuantity();
            }
        }
        if (!national) {
            valueStockCash *= cons.conDolarPrice().get().getAsk();
        }
        return new BalanceDTO(valueStockCash);
    }

    public List<StockDTO> minePortfolio() {
        List<StockDTO> myPortfolio = new ArrayList<>();
        for (Stock stock : stockEntity.findAll()) {
            if (stock.getBuy()) {
                StockPrice stockPrice = cons.conStockPrice(stock.getStock());
                StockDTO stockDTO = new StockDTO(stock.getStock(),
                        stockPrice.getClose(),
                        stock.getQuantity(),
                        stock.getNational());
                myPortfolio.add(stockDTO);
            } else {
                for (StockDTO stockdto : myPortfolio) {
                    if (stockdto.getStock().equals(stock.getStock())) {
                        stockdto.setQuantity(stockdto.getQuantity() - stock.getQuantity());
                    }
                }
            }
        }
        myPortfolio.removeIf(stockDTO -> stockDTO.getQuantity() == 0);
        return myPortfolio;
    }
}
