package com.saimon.Stock.portfolio.Services;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.Api.stock.StockPrice;
import com.saimon.Stock.portfolio.DTO.BalanceDTO;
import com.saimon.Stock.portfolio.DTO.StockDTO;
import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.Database.Repository.BalanceRepository;
import com.saimon.Stock.portfolio.Database.Repository.CashRepository;
import com.saimon.Stock.portfolio.Database.Repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    private Logger Log = LoggerFactory.getLogger(BalanceService.class);
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private Consumer cons;

    public BalanceDTO scheduleBalance() {
        BalanceDTO usaBalance = balance(false);
        BalanceDTO brBalance = balance(true);
        BalanceDTO brStock = balanceCashStockPortfolio(true);
        BalanceDTO usaStock = balanceCashStockPortfolio(false);
        Double totalBalance = usaBalance.getBalance() + brBalance.getBalance() + usaStock.getBalance() + brStock.getBalance();
        Balance balance = new Balance(usaBalance.getBalance(), brBalance.getBalance(), usaStock.getBalance(), brStock.getBalance(), totalBalance);
        balanceRepository.save(balance);
        return new BalanceDTO(balance.getTotalBalance());
    }

    public BalanceDTO balance(Boolean national) {
        double balanceValue = 0D;
        if (national == null) {
            for (Cash cash : cashRepository.findAll()) {
                double cashValue = cash.getCashValue();
                if (!cash.getNational()) {
                    cashValue *= cons.conDolarPrice().get().getAsk();
                }
                balanceValue += cashValue;
            }
        } else {
            for (Cash cash : cashRepository.findAll()) {
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
        boolean national = false;
        if (stock.contains(".sa")) {
            national = true;
        }
        for (Stock oneStock : stockRepository.findAll()) {
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
        return new StockDTO(stock, (averageValue / quantity), quantity, national);
    }

    public BalanceDTO balanceCashStockPortfolio(Boolean national) {
        Double completeBalance = 0D;
        BalanceDTO cashBalance = balance(null);
        if (national == null) {
            for (Stock stock : stockRepository.findAll()) {
                if (stock.getBuy()) {
                    StockPrice findStock = cons.conStockPrice(stock.getStock());
                    Double patrimony = stock.getQuantity() * findStock.getClose();
                    if (!stock.getNational()) {
                        patrimony *= cons.conDolarPrice().get().getAsk();
                    }
                    completeBalance += patrimony;
                }
            }
        } else {
            for (Stock stock : stockRepository.findAll()) {
                if (stock.getBuy()) {
                    if (stock.getNational() == national) {
                        StockPrice findStock = cons.conStockPrice(stock.getStock());
                        Double patrimony = stock.getQuantity() * findStock.getClose();
                        if (!stock.getNational()) {
                            patrimony *= cons.conDolarPrice().get().getAsk();
                        }
                        completeBalance += patrimony;
                    }
                }
            }
        }
        return new BalanceDTO(completeBalance);
    }
}
