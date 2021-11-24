package com.saimon.Stock.portfolio.DTO;

import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashDTOConverter {

    public Optional<CashDTO> converter(Cash cash) throws Exception {
        try {
            CashDTO cashDto = new CashDTO(cash.getCashValue(), cash.getNational());
            return Optional.of(cashDto);
        } catch (Exception e) {
            throw new Exception("Error converter");
        }

    }
}
