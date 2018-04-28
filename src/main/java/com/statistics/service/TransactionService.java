package com.statistics.service;

import com.statistics.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class TransactionService {

    private ConcurrentSkipListMap<Date, Transaction> transactions = new ConcurrentSkipListMap<>();

    private final StatisticsService statisticsService;

    @Autowired
    public TransactionService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void recordTransaction(final Transaction transaction) {
        Date startTime = Date.from(Instant.now().minusSeconds(60L));
        transactions.put(transaction.getTimestamp(), transaction);

        ConcurrentSkipListMap<Date, Transaction> transactionFromStartTime = truncateOldTransactions(transactions, startTime);
        statisticsService.update(transactionFromStartTime.values());
    }

    private ConcurrentSkipListMap<Date, Transaction> truncateOldTransactions(final ConcurrentSkipListMap<Date, Transaction> transactions, final Date startTime) {
        transactions.headMap(startTime).clear();
        return transactions;
    }
}
