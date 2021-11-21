package com.saimon.Stock.portfolio.api.consumerApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saimon.Stock.portfolio.api.jsonObj.stockPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class stockInfo {
    private stockPrice stockPrice;
    private Logger Log = LoggerFactory.getLogger(stockInfo.class);

    public stockInfo(@JsonProperty("result") ArrayList<?> chart) {
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

        stockPrice stockPrice = new stockPrice(symbol, low, open, close, high, volume);
        this.stockPrice = stockPrice;
    }

    public stockPrice getStockInfo() {
        return this.stockPrice;
    }

}

