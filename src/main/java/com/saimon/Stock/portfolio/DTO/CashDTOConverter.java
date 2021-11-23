package com.saimon.Stock.portfolio.DTO;

import com.saimon.Stock.portfolio.Database.Model.Cash;
import org.springframework.stereotype.Service;

@Service
public class CashDTOConverter {

    public CashDTO converter(Cash cash){
        return new CashDTO(cash.getCashValue(), cash.getNational());
    }
}
