package com.saimon.Stock.portfolio.DTO;

import java.util.Date;

public class BalanceDTO {
    private Double balance;
    private Date datetime;

    public BalanceDTO(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }
}
