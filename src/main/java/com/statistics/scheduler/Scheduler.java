package com.statistics.scheduler;

import com.statistics.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    public static final String CRON_STRING = "1 * * * * ?";
    private TransactionService transactionService;

    @Autowired
    public Scheduler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Scheduled(cron = CRON_STRING)
    public void updateTransactions() {
       transactionService.updateTransactions();
    }
}