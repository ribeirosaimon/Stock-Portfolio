package com.saimon.Stock.portfolio.api;

import com.saimon.Stock.portfolio.api.consumerApi.dolarConsumerApi;
import com.saimon.Stock.portfolio.api.consumerApi.stockConsumerApi;
import com.saimon.Stock.portfolio.api.jsonObj.dolarPrice;
import com.saimon.Stock.portfolio.api.jsonObj.stockPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

//https://query1.finance.yahoo.com/v8/finance/chart/AAPL?symbol=AAPL&&interval=1d
@Service
public class consumer {

    public Optional<dolarPrice> conDolarPrice() {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("economia.awesomeapi.com.br")
                .path("last/USD-BRL")
                .build();
        ResponseEntity<dolarConsumerApi> entity = template.getForEntity(uri.toUriString(),
                dolarConsumerApi.class);
        var price = entity.getBody().getDolar();
        return Optional.of(price);
    }

    public Optional<stockPrice> conStockPrice(String stockSymbol) {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("query1.finance.yahoo.com")
                .path(String.format("v8/finance/chart/%s", stockSymbol))
                .queryParam(String.format("?symbol=%s&&interval=1d", stockSymbol))
                .build();
        ResponseEntity<stockConsumerApi> entity = template.getForEntity(uri.toUriString(),
                stockConsumerApi.class);
        var price = entity.getBody();
        return Optional.of(price);
    }

}
