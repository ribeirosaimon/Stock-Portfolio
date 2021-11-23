package com.saimon.Stock.portfolio.controllers;

import com.saimon.Stock.portfolio.DTO.CashDTO;
import com.saimon.Stock.portfolio.DTO.CashDTOConverter;
import com.saimon.Stock.portfolio.Database.Entity.CashEntity;
import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.api.Consumer;
import com.saimon.Stock.portfolio.services.CashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class CashController {
    private Logger Log = LoggerFactory.getLogger(CashController.class);
    private final String DEPOSIT_URI = "/deposit";
    private final String WITHDRAW_URI = "/withdraw/{national}/{value}";
    private final String BALANCE_URI = "/balance/{national}";
    @Autowired
    private Consumer cons;
    @Autowired
    private StockEntity stockEntity;
    @Autowired
    private CashEntity cashEntity;
    @Autowired
    private CashService cashService;
    @Autowired
    private CashDTOConverter cashDTOConverter;

    @GetMapping(DEPOSIT_URI)
    public CashDTO deposit(@RequestParam("national") boolean national,
                           @RequestParam("value") double value,
                           @RequestParam(value = "dolar", required = false) double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DEPOSIT_URI)
                .toString());
        Cash cashValue = cashService.deposit(value, national, dolar);
        return cashDTOConverter.converter(cashValue);
//        return ResponseEntity
//                .created(uri)
//                .body(Optional
//                        .of()
//                        .orElseThrow(() -> new Exception("Error to deposit")));
    }

    @GetMapping(WITHDRAW_URI)
    public ResponseEntity<CashDTO> withdraw(@RequestParam("national") boolean national,
                                            @RequestParam("value") double value,
                                            @RequestParam(value = "dolar", required = false) double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(WITHDRAW_URI)
                .toString());
        cashService.withdraw(value, national, dolar);
        return ResponseEntity
                .created(uri)
                .body(Optional
                        .of(new CashDTO(value, national))
                        .orElseThrow(() -> new Exception("Error to withdraw")));
    }

    @GetMapping(BALANCE_URI)
    public String balance(@PathVariable("national") boolean national) {
        return String.valueOf(cashService.balance(national));
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

        Stock stock1 = new Stock(acao1.getSymbol(), 17.89, 251D);
        Stock stock2 = new Stock(acao2.getSymbol(), 5.32, 1000D);
        Stock stock3 = new Stock(acao3.getSymbol(), 4.74, 1600D);
        Stock stock4 = new Stock(acao4.getSymbol(), 29.27, 100D);
        Stock stock5 = new Stock(acao5.getSymbol(), 1628.42, 3.55932);
        Stock stock6 = new Stock(acao6.getSymbol(), 71.17, 18D);
        Stock stock7 = new Stock(acao7.getSymbol(), 66.92, 29D);

        stockEntity.save(stock1);
        stockEntity.save(stock2);
        stockEntity.save(stock3);
        stockEntity.save(stock4);
        stockEntity.save(stock5);
        stockEntity.save(stock6);
        stockEntity.save(stock7);

        return "Stock Ok";
    }
}
