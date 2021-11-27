package com.saimon.Stock.portfolio.DTO;

import java.util.Objects;

public class StockDTO {
    private String stock;
    private Double value;
    private Double quantity;

    public StockDTO(String stock, Double value, Double quantity) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
    }

    public String getStock() {
        return stock;
    }

    public Double getValue() {
        return value;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDTO)) return false;
        StockDTO stockDTO = (StockDTO) o;
        return Objects.equals(getStock(), stockDTO.getStock()) && Objects.equals(getValue(), stockDTO.getValue()) && Objects.equals(getQuantity(), stockDTO.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStock(), getValue(), getQuantity());
    }
}
