package com.saimon.Stock.portfolio.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoMoneyException extends RuntimeException {
    public NoMoneyException() {
        super("You don't have money");
    }
}
