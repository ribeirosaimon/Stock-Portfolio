package com.saimon.Stock.portfolio.Database.Entity;

import com.saimon.Stock.portfolio.Database.Model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceEntity extends JpaRepository<Balance, Long> {
}
