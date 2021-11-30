package com.saimon.Stock.portfolio.Controllers;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.DTO.CashDTO;
import com.saimon.Stock.portfolio.DTO.PortfolioDTO;
import com.saimon.Stock.portfolio.Database.Entity.BalanceEntity;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.Services.BalanceService;
import com.saimon.Stock.portfolio.Services.CashService;
import com.saimon.Stock.portfolio.Services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping
public class CashController {
    private Logger Log = LoggerFactory.getLogger(CashController.class);
    private final String DEPOSIT_URI = "/deposit";
    private final String WITHDRAW_URI = "/withdraw";
    private final String BALANCE_URI = "/balance";
    @Autowired
    private Consumer cons;
    @Autowired
    private StockEntity stockEntity;
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private CashService cashService;
    @Autowired
    private BalanceEntity balanceEntity;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private StockService stockService;

    @GetMapping(DEPOSIT_URI)
    public ResponseEntity<CashDTO> deposit(@RequestParam("national") Boolean national,
                                           @RequestParam("value") Double value,
                                           @RequestParam(value = "dolar", required = false) Double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DEPOSIT_URI)
                .toString());
        Cash cashValue = cashService.deposit(value, national, dolar);
        return ResponseEntity
                .created(uri)
                .body(Optional.of(new CashDTO(cashValue.getCashValue(), cashValue.getNational()))
                        .orElseThrow(() -> new Exception("Error to deposit")));
    }

    @GetMapping(WITHDRAW_URI)
    public ResponseEntity<CashDTO> withdraw(@RequestParam("national") Boolean national,
                                            @RequestParam("value") Double value,
                                            @RequestParam(value = "dolar", required = false) Double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(WITHDRAW_URI)
                .toString());
        Cash cashValue = cashService.withdraw((value * -1), national, dolar);
        return ResponseEntity
                .created(uri)
                .body(Optional.of(new CashDTO(cashValue.getCashValue(), cashValue.getNational()))
                        .orElseThrow(() -> new Exception("Error to withdraw")));
    }

    @GetMapping(BALANCE_URI)
    public String balance(@RequestParam(value = "national", required = false) Integer national) {
        Boolean nationalBoolean = null;
        if (national == 1) {
            nationalBoolean = true;
        } else if (national == 0) {
            nationalBoolean = false;
        } else if (national == 2) {
            nationalBoolean = null;
        }
        return balanceService.balance(nationalBoolean).getBalance().toString();
    }

    @GetMapping("/buy")
    public String buyStock() {
        Stock stock1 = new Stock("ctnm4.sa", 5.32, 1000D, true, true);
        Stock stock2 = new Stock("movi3.sa", 17.89, 251D, true, true);
        Stock stock3 = new Stock("sapr4.sa", 3.78, 1600D, true, true);
        Stock stock4 = new Stock("sula11.sa", 25.42, 100D, true, true);
        stockService.buyStock(stock1);
        stockService.buyStock(stock2);
        stockService.buyStock(stock3);
        stockService.buyStock(stock4);
        return "BUY Ok";
    }

    @GetMapping("/sell")
    public String sellStock() {
        var acao1 = cons.conStockPrice("movi3.sa");
        Stock stock1 = new Stock(acao1.getSymbol(), 17.89, 101D, true, false);
        stockService.sellStock(stock1);
        return "SELL OK";
    }

    @GetMapping("/portfolio")
    public PortfolioDTO portfolioStock() {
        Balance balance = balanceEntity.findTopByOrderByIdDesc().get();
        return new PortfolioDTO(balance.getBrBalance(),
                balance.getUsaBalance(),
                balance.getBrStock(),
                balance.getUsaStock()
        );
    }

}
