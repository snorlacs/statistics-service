package com.statistics.service;

import com.statistics.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class TransactionService {

    public static final long TRANSACTION_WINDOW_SECONDS = 60L;
    private ConcurrentSkipListMap<Date, Transaction> transactions = new ConcurrentSkipListMap<>();

    private final StatisticsService statisticsService;

    @Autowired
    public TransactionService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void recordTransaction(final Transaction transaction) {
        transactions.put(transaction.getTimestamp(), transaction);
        updateTransactions();
    }

    public void updateTransactions() {
        if(!transactions.isEmpty()) {
            ConcurrentSkipListMap<Date, Transaction> transactionInLast60Seconds = truncateOldTransactions(transactions, Date.from(Instant.now().minusSeconds(TRANSACTION_WINDOW_SECONDS)));
            statisticsService.update(transactionInLast60Seconds.values());
        }
    }

    private ConcurrentSkipListMap<Date, Transaction> truncateOldTransactions(final ConcurrentSkipListMap<Date, Transaction> transactions, final Date startTime) {
        transactions.headMap(startTime).clear();
        return transactions;
    }
}
