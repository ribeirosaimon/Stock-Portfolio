package com.saimon.Stock.portfolio.DTO;

import java.util.Objects;

public class StockDTO {
    private String stock;
    private Double value;
    private Double quantity;
    private Boolean national;

    public StockDTO(String stock, Double value, Double quantity, Boolean national) {
        this.stock = stock;
        this.value = value;
        this.quantity = quantity;
        this.national = national;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getNational() {
        return national;
    }

    public void setNational(Boolean national) {
        this.national = national;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDTO)) return false;
        StockDTO stockDTO = (StockDTO) o;
        return Objects.equals(getStock(), stockDTO.getStock()) && Objects.equals(getValue(), stockDTO.getValue()) && Objects.equals(getQuantity(), stockDTO.getQuantity()) && Objects.equals(getNational(), stockDTO.getNational());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStock(), getValue(), getQuantity(), getNational());
    }
}
