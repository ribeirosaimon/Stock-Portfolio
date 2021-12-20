package com.saimon.Stock.portfolio.Database.Repository;

import com.saimon.Stock.portfolio.Database.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
