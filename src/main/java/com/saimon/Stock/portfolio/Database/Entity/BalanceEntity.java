package com.saimon.Stock.portfolio.Database.Entity;

import com.saimon.Stock.portfolio.Database.Model.Balance;
import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceEntity extends JpaRepository<Balance, Long> {
    Optional<Balance> findTopByOrderByIdDesc();
}
