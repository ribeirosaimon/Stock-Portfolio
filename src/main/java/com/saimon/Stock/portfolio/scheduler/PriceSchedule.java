package com.saimon.Stock.portfolio.scheduler;

import com.saimon.Stock.portfolio.Database.Entity.DolarRepository;
import com.saimon.Stock.portfolio.Database.Model.DolarModel;
import com.saimon.Stock.portfolio.Output.OutputLog;
import com.saimon.Stock.portfolio.api.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceSchedule {
    private static final Logger log = LoggerFactory.getLogger(PriceSchedule.class);

    @Autowired
    Consumer consumer;

    @Autowired
    DolarRepository dolarRepository;

    @Autowired
    OutputLog outputLog;

    @Scheduled(fixedDelay = 1000L)
    public void teste() {
        var price = consumer.fetch().getBody();
        DolarModel priceDolar = new DolarModel(price.getHigh(), price.getLow(), price.getTimestamp());
        outputLog.PrintPriceOutput(priceDolar);
        dolarRepository.save(priceDolar);
    }
}
