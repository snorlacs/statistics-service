package com.statistics.service;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @Test
    public void shouldUpdateStatistics() throws Exception {
        StatisticsService statisticsService = new StatisticsService();

        Transaction transaction1 = new Transaction(2.33, new Date(Instant.now().minusSeconds(12L).toEpochMilli()));
        Transaction transaction2 = new Transaction(4.33, new Date(Instant.now().toEpochMilli()));
        ConcurrentSkipListMap<Date, Transaction> transactions = new ConcurrentSkipListMap<Date, Transaction>() {{
            put(transaction1.getTimestamp(), transaction1);
            put(transaction2.getTimestamp(), transaction2);
        }};

        statisticsService.update(transactions.values());

        assertEquals(statisticsService.getStatistics(), new Statistics(6.66,3.33,4.33,2.33,2));
    }
}