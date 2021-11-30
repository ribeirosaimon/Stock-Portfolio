package com.saimon.Stock.portfolio.DTO;

public class PortfolioDTO {
    private Double brCash;
    private Double brStock;
    private Double brTotal;
    private Double usaCash;
    private Double usaStock;
    private Double usaTotal;
    private Double total;


    public PortfolioDTO(Double brCash, Double usaCash, Double brStock, Double usaStock) {
        this.brCash = brCash;
        this.usaCash = usaCash;
        this.brStock = brStock;
        this.usaStock = usaStock;
    }

    public Double getBrCash() {
        return brCash;
    }

    public Double getUsaCash() {
        return usaCash;
    }

    public Double getBrStock() {
        return brStock;
    }

    public Double getUsaStock() {
        return usaStock;
    }

    public Double getTotal() {
        total = brCash + usaCash + brStock + usaStock;
        return total;
    }

    public Double getBrTotal() {
        return brStock + brCash;
    }

    public Double getUsaTotal() {
        return usaStock + usaCash;
    }
}
