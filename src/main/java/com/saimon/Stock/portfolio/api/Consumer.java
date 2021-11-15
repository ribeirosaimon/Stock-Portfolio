package com.saimon.Stock.portfolio.api;

import com.saimon.Stock.portfolio.scheduler.PriceSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class Consumer {
    private static final Logger log = LoggerFactory.getLogger(PriceSchedule.class);

    public ResponseEntity<DolarPrice> fetch() {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("economia.awesomeapi.com.br")
                .path("last/USD-BRL")
                .build();
        ResponseEntity<FullJson> entity = template.getForEntity(uri.toUriString(), FullJson.class);
        var price = entity.getBody().getDolar();
        return ResponseEntity.of(Optional.of(price));
    }
}
