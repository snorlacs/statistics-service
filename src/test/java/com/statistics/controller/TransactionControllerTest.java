package com.statistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statistics.domain.Transaction;
import com.statistics.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        TransactionController transactionController = new TransactionController(transactionService);
        mvc = MockMvcBuilders
                .standaloneSetup(transactionController)
                .build();
    }

    @Test
    public void shouldPostTransactions() throws Exception {
        Transaction transaction = new Transaction(2.33, new Date(Instant.now().toEpochMilli()));
        String requestString = objectMapper.writeValueAsString(transaction);

        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        verify(transactionService).recordTransaction(transaction);
    }
}