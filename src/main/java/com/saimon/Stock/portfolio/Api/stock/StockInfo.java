package com.saimon.Stock.portfolio.Api.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StockInfo {
    private StockPrice stockPrice;
    private Logger Log = LoggerFactory.getLogger(StockInfo.class);

    public StockInfo(@JsonProperty("result") ArrayList<?> chart) {
        LinkedHashMap<?, ?> result = (LinkedHashMap<?, ?>) chart.get(0);
        LinkedHashMap<?, ?> stockValues = (LinkedHashMap<?, ?>) result.get("meta");
        LinkedHashMap<?, ?> indicators = (LinkedHashMap<?, ?>) result.get("indicators");
        ArrayList<?> quote = (ArrayList<?>) indicators.get("quote");
        LinkedHashMap<?, ?> quoteMap = (LinkedHashMap<?, ?>) quote.get(0);

        ArrayList<?> lowList = (ArrayList<?>) quoteMap.get("low");
        ArrayList<?> openList = (ArrayList<?>) quoteMap.get("open");
        ArrayList<?> closeList = (ArrayList<?>) quoteMap.get("close");
        ArrayList<?> highList = (ArrayList<?>) quoteMap.get("high");
        ArrayList<?> volumeList = (ArrayList<?>) quoteMap.get("volume");

        String symbol = (String) stockValues.get("symbol");
        Double low = (Double) lowList.get(0);
        Double open = (Double) openList.get(0);
        Double close = (Double) closeList.get(0);
        Double high = (Double) highList.get(0);
        Long volume = ((Number) volumeList.get(0)).longValue();

        StockPrice stockPrice = new StockPrice(symbol.toLowerCase(), low, open, close, high, volume);
        this.stockPrice = stockPrice;
    }

    public StockPrice getStockInfo() {
        return this.stockPrice;
    }

}

