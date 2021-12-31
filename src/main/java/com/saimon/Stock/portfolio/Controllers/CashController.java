package com.saimon.Stock.portfolio.Controllers;

import com.saimon.Stock.portfolio.Api.Consumer;
import com.saimon.Stock.portfolio.DTO.CashDTO;
import com.saimon.Stock.portfolio.DTO.PortfolioDTO;
import com.saimon.Stock.portfolio.DTO.StockDTO;
import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import com.saimon.Stock.portfolio.Database.Model.Stock;
import com.saimon.Stock.portfolio.Database.Repository.BalanceRepository;
import com.saimon.Stock.portfolio.Database.Repository.CashRepository;
import com.saimon.Stock.portfolio.Database.Repository.StockRepository;
import com.saimon.Stock.portfolio.Services.BalanceService;
import com.saimon.Stock.portfolio.Services.CashService;
import com.saimon.Stock.portfolio.Services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping
public class CashController {
    private Logger Log = LoggerFactory.getLogger(CashController.class);
    private final String DEPOSIT_URI = "/deposit";
    private final String WITHDRAW_URI = "/withdraw";
    private final String BALANCE_URI = "/balance";
    @Autowired
    private Consumer cons;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private CashRepository cashRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private StockService stockService;

    @PostMapping(DEPOSIT_URI)
    public ResponseEntity<CashDTO> deposit(@RequestParam("national") Boolean national,
                                           @RequestParam("value") Double value,
                                           @RequestParam(value = "dolar", required = false) Double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DEPOSIT_URI)
                .toString());
        Cash cashValue = cashService.deposit(value, national, dolar);
        return ResponseEntity
                .created(uri)
                .body(Optional.of(new CashDTO(cashValue.getCashValue(), cashValue.getNational()))
                        .orElseThrow(() -> new Exception("Error to deposit")));
    }

    @PostMapping(WITHDRAW_URI)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CashDTO> withdraw(@RequestParam("national") Boolean national,
                                            @RequestParam("value") Double value,
                                            @RequestParam(value = "dolar", required = false) Double dolar)
            throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(WITHDRAW_URI)
                .toString());
        Cash cashValue = cashService.withdraw((value * -1), national, dolar);
        return ResponseEntity
                .created(uri)
                .body(Optional.of(new CashDTO(cashValue.getCashValue(), cashValue.getNational()))
                        .orElseThrow(() -> new Exception("Error to withdraw")));
    }

    @GetMapping(BALANCE_URI)
    @ResponseStatus(HttpStatus.OK)
    public String balance(@RequestParam(value = "national", required = false) Integer national) {
        Boolean nationalBoolean = null;
        if (national == 1) {
            nationalBoolean = true;
        } else if (national == 0) {
            nationalBoolean = false;
        } else if (national == 2) {
            nationalBoolean = null;
        }
        return balanceService.balance(nationalBoolean).getBalance().toString();
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public StockDTO buyStock(@RequestParam("stock") String ticket,
                             @RequestParam("value") Double value,
                             @RequestParam("quantity") Double qtd,
                             @RequestParam(value = "dolar", required = false) Double dolar) {
        Boolean national = false;
        if (!ticket.contains(".sa")) {
            national = true;
        }

        Stock stock = new Stock(ticket, value, qtd, national, true);
        stockService.buyStock(stock);
        return new StockDTO(stock.getStock(), stock.getValue(), stock.getQuantity(), stock.getNational());
    }

    @PostMapping("/sell")
    @ResponseStatus(HttpStatus.OK)
    public StockDTO sellStock(@RequestParam("stock") String ticket,
                            @RequestParam("value") Double value,
                            @RequestParam("quantity") Double qtd,
                            @RequestParam(value = "dolar", required = false) Double dolar) {
        Boolean national = false;
        if (!ticket.contains(".sa")) {
            national = true;
        }
        Stock stock = new Stock(ticket, value, qtd, national, false);
        stockService.sellStock(stock);
        return new StockDTO(stock.getStock(), stock.getValue(), stock.getQuantity(), stock.getNational());
    }

    @GetMapping("/portfolio")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO portfolioStock() {
        Balance balance = balanceRepository.findTopByOrderByIdDesc().get();
        return new PortfolioDTO(balance.getBrBalance(),
                balance.getUsaBalance(),
                balance.getBrStock(),
                balance.getUsaStock()
        );
    }

}
