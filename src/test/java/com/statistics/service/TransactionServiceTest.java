package com.statistics.service;

import com.statistics.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private StatisticsService statisticsService;

    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        transactionService = new TransactionService(statisticsService);

    }

    @Test
    public void shouldUpdateStatisticsOnRecordingATransaction() throws Exception {
        Transaction transaction = new Transaction(2.33, new Date(Instant.now().toEpochMilli()));
        transactionService.recordTransaction(transaction);

        ConcurrentSkipListMap<Date, Transaction> validMap = new ConcurrentSkipListMap<Date, Transaction>() {{
            put(transaction.getTimestamp(), transaction);
        }};

        ArgumentCaptor<Collection> captor = ArgumentCaptor.forClass(Collection.class);
        verify(statisticsService).update(captor.capture());
        assertTrue(captor.getValue().containsAll(validMap.values()));
    }

    @Test
    public void shouldCallUpdateStatisticsForAllTransactionsWithinPastMin() throws Exception {
        Transaction transaction1 = new Transaction(2.33, new Date(Instant.now().minusSeconds(12L).toEpochMilli()));
        transactionService.recordTransaction(transaction1);

        Transaction transaction2 = new Transaction(4.33, new Date(Instant.now().toEpochMilli()));
        transactionService.recordTransaction(transaction2);

        ConcurrentSkipListMap<Date, Transaction> validMap = new ConcurrentSkipListMap<Date, Transaction>() {{
            put(transaction1.getTimestamp(), transaction1);
            put(transaction2.getTimestamp(), transaction2);
        }};

        ArgumentCaptor<Collection> captor = ArgumentCaptor.forClass(Collection.class);
        verify(statisticsService, times(2)).update(captor.capture());
        assertTrue(captor.getAllValues().get(1).containsAll(validMap.values()));
        assertEquals(captor.getAllValues().get(1).size(), 2);
    }

    @Test
    public void shouldCallUpdateStatisticsOnlyForTransactionsWithinPastMin() throws Exception {
        Transaction oldTransaction = new Transaction(2.33, new Date(Instant.now().minusSeconds(61L).toEpochMilli()));
        transactionService.recordTransaction(oldTransaction);

        Transaction newTransaction = new Transaction(4.33, new Date(Instant.now().toEpochMilli()));
        transactionService.recordTransaction(newTransaction);

        ConcurrentSkipListMap<Date, Transaction> validMap = new ConcurrentSkipListMap<Date, Transaction>() {{
            put(newTransaction.getTimestamp(), newTransaction);
        }};

        ArgumentCaptor<Collection> captor = ArgumentCaptor.forClass(Collection.class);
        verify(statisticsService, times(2)).update(captor.capture());
        assertTrue(captor.getAllValues().get(1).containsAll(validMap.values()));
        assertEquals(captor.getAllValues().get(1).size(), 1);
    }
}