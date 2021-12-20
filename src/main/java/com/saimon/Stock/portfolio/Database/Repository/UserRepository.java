package com.saimon.Stock.portfolio.Database.Repository;

import com.saimon.Stock.portfolio.Database.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByLogin(String Login);
}
