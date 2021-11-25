package com.saimon.Stock.portfolio.Schedule;

import com.saimon.Stock.portfolio.Services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleBalance {
    @Autowired
    private BalanceService balanceService;

    @Scheduled(fixedDelay = 6000)
    public void saveBalance(){
        balanceService.scheduleBalance();
    }
}
