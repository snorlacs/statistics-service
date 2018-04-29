package com.statistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statistics.domain.Statistics;
import com.statistics.exception.NoTransactionsMadeException;
import com.statistics.service.StatisticsService;
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

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        StatisticsController statisticsController = new StatisticsController(statisticsService);

        mockMvc = MockMvcBuilders
                    .standaloneSetup(statisticsController)
                    .setControllerAdvice(new ControllerErrorHandler())
                    .build();
    }

    @Test
    public void shouldGetStatistics() throws Exception {
        Statistics statistics = new Statistics(2.33, 2.33, 2.33, 2.33, 1);
        String responseString = objectMapper.writeValueAsString(statistics);
        when(statisticsService.getStatistics()).thenReturn(statistics);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseString));
    }

    @Test
    public void shouldReturnNoContentWhenThereIsNoTransactionMadeInLast60Seconds() throws Exception {
        when(statisticsService.getStatistics()).thenThrow(new NoTransactionsMadeException("No Transaction made in last 60 seconds"));

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}