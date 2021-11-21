package com.saimon.Stock.portfolio.Database.Entity;

import com.saimon.Stock.portfolio.Database.Model.portfolioStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface portfolioEntity extends JpaRepository<portfolioStock, Long> {
}
