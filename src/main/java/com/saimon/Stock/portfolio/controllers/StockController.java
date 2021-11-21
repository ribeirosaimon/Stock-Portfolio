package com.saimon.Stock.portfolio.controllers;

import com.saimon.Stock.portfolio.api.consumer;
import com.saimon.Stock.portfolio.api.jsonObj.stockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    consumer cons;

    @GetMapping
    public stockPrice teste() {
        return cons.conStockPrice("movi3.sa");
    }
}
