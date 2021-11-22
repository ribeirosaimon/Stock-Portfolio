package com.saimon.Stock.portfolio.Database.Entity;

import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntity extends JpaRepository<Stock, Long> {
}
