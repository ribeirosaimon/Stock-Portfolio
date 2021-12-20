package com.saimon.Stock.portfolio.Database.Repository;

import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<Cash, Long> {

}
