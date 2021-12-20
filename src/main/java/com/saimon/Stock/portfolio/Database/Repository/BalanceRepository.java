package com.saimon.Stock.portfolio.Database.Repository;

import com.saimon.Stock.portfolio.Database.Model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findTopByOrderByIdDesc();
}
