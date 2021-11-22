package com.saimon.Stock.portfolio.controllers;

import com.saimon.Stock.portfolio.Database.Entity.StockEntity;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.api.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    Consumer cons;
    @Autowired
    StockEntity entity;

    @GetMapping
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
        Stock stock6 = new Stock(acao6.getSymbol(), 71.17,18D);
        Stock stock7 = new Stock(acao7.getSymbol(), 66.92,29D);

//        entity.save(stock1);
//        entity.save(stock2);
//        entity.save(stock3);
//        entity.save(stock4);
        entity.save(stock5);
        entity.save(stock6);
        entity.save(stock7);

        return "ok";
    }
}
