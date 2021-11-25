package com.saimon.Stock.portfolio.Api;

import com.saimon.Stock.portfolio.Api.dolar.Response;
import com.saimon.Stock.portfolio.Api.dolar.DolarPrice;
import com.saimon.Stock.portfolio.Api.stock.StockPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

//https://query1.finance.yahoo.com/v8/finance/chart/AAPL?symbol=AAPL&&interval=1d

@Service
public class Consumer {
    private Logger Log = LoggerFactory.getLogger(Consumer.class);

    public Optional<DolarPrice> conDolarPrice() {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("economia.awesomeapi.com.br")
                .path("last/USD-BRL")
                .build();
        ResponseEntity<Response> entity = template.getForEntity(uri.toUriString(),
                Response.class);
        if (entity.getStatusCode() != HttpStatus.OK) {
            throw new NullPointerException(String.format("Entity is null. Response code: %s", entity.getStatusCode()));
        }
        return Optional.ofNullable(entity.getBody().getDolar());
    }

    public StockPrice conStockPrice(String stockSymbol) {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("query1.finance.yahoo.com")
                .path(String.format("v8/finance/chart/%s", stockSymbol))
                .queryParam(String.format("?symbol=%s&&interval=1d", stockSymbol))
                .build();
        ResponseEntity<com.saimon.Stock.portfolio.Api.stock.Response> entity = template.getForEntity(uri.toUriString(), com.saimon.Stock.portfolio.Api.stock.Response.class);
        if (entity.getStatusCode() != HttpStatus.OK) {
            throw new NullPointerException(String.format("Entity is null. Reponse Code: %s", entity.getStatusCode()));
        }
        return entity.getBody().getChartJson().getStockInfo();

    }

}
