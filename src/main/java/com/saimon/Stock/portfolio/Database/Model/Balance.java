package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double usaBalance;
    private Double brBalance;
    private Double usaStock;
    private Double brStock;
    private Double totalBalance;
    private Date timestamp = new Date();

    Balance() {

    }

    public Balance(Double usaBalance, Double brBalance, Double usaStock, Double brStock, Double totalBalance) {
        this.usaBalance = usaBalance;
        this.brBalance = brBalance;
        this.usaStock = usaStock;
        this.brStock = brStock;
        this.totalBalance = totalBalance;
    }

    public Double getUsaBalance() {
        return usaBalance;
    }

    public Double getBrBalance() {
        return brBalance;
    }

    public Double getUsaStock() {
        return usaStock;
    }

    public Double getBrStock() {
        return brStock;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
