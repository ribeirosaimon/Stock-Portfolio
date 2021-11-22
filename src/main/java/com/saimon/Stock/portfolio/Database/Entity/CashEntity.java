package com.saimon.Stock.portfolio.Database.Entity;

import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashEntity extends JpaRepository<Cash, Long> {
}
