package com.saimon.Stock.portfolio.Controllers;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.DTO.CashDTO;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
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

    @GetMapping("/testeinit")
    public String testeInicial() {
        var acao1 = cons.conStockPrice("movi3.sa");
        var acao2 = cons.conStockPrice("ctnm4.sa");
        var acao3 = cons.conStockPrice("sapr4.sa");
        var acao4 = cons.conStockPrice("sula11.sa");
        var acao5 = cons.conStockPrice("meli");
        var acao6 = cons.conStockPrice("mu");
        var acao7 = cons.conStockPrice("mchi");

        Stock stock1 = new Stock(acao1.getSymbol(), 17.89, 251D, true);
//        Stock stock2 = new Stock(acao2.getSymbol(), 5.32, 1000D, true);
//        Stock stock3 = new Stock(acao3.getSymbol(), 4.74, 1600D, true);
//        Stock stock4 = new Stock(acao4.getSymbol(), 29.27, 100D, true);
//        Stock stock5 = new Stock(acao5.getSymbol(), 1628.42, 3.55932, false);
//        Stock stock6 = new Stock(acao6.getSymbol(), 71.17, 18D, false);
//        Stock stock7 = new Stock(acao7.getSymbol(), 66.92, 29D, false);

        stockService.buyStock(stock1);
//        stockEntity.save(stock2);
//        stockEntity.save(stock3);
//        stockEntity.save(stock4);
//        stockEntity.save(stock5);
//        stockEntity.save(stock6);
//        stockEntity.save(stock7);

        return "Stock Ok";
    }
}
