package com.saimon.Stock.portfolio.DTO;

import java.util.Date;

public class CashDTO {
    private Double value;
    private Boolean national;
    private Date timestamp;

    public CashDTO(Double value, Boolean national) {
        this.value = value;
        this.national = national;
        this.timestamp = new Date();
    }

    public Double getValue() {
        return value;
    }

    public Boolean getNational() {
        return national;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
