package com.saimon.Stock.portfolio.Output;

import com.saimon.Stock.portfolio.Database.Model.DolarModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OutputLogImpl implements OutputLog {
    private static final Logger log = LoggerFactory.getLogger(OutputLogImpl.class);

    @Override
    public void PrintPriceOutput(DolarModel dolarPrice) {
        log.info("---------------------");
        log.info("High Dolar Price: {}", dolarPrice.getHigh());
        log.info("Low Dolar Price: {}", dolarPrice.getLow());
        log.info("Timestamp: {}", dolarPrice.getTimestamp());
        log.info("---------------------");
    }
}
