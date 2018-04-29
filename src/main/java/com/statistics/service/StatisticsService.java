package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import com.statistics.exception.NoTransactionsMadeException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private Statistics statistics;

    public void update(Collection<Transaction> transactions) {
        if(transactions.isEmpty()) {
            statistics = null;
        } else {
            DoubleSummaryStatistics doubleSummaryStatistics = transactions.stream().collect(Collectors.summarizingDouble(Transaction::getAmount));
            statistics = Statistics.from(doubleSummaryStatistics);
        }
    }

    public Statistics getStatistics() {
        if(statistics == null) {
            throw new NoTransactionsMadeException("No transactions made in last 60 seconds");
        }
        return statistics;
    }
}
