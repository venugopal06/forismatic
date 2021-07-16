package com.gremlin.forismatic.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gremlin.forismatic.dto.ForismaticDTO;
import com.gremlin.forismatic.service.ForismaticRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForismaticRunnerTest {

    @Mock
    ForismaticRestClient forismaticRestClient;
    @InjectMocks
    ForismaticRunner forismaticRunner;

    @Test
    void run_With_No_Arguments_Test() throws JsonProcessingException {
        ForismaticDTO mockForismaticDTO = new ForismaticDTO();
        when(forismaticRestClient.getForismaticQuote(anyString())).thenReturn(mockForismaticDTO);
        forismaticRunner.run();
        verify(forismaticRestClient).getForismaticQuote(anyString());
    }

    @Test
    void run_With_Russian_Language_Test() throws JsonProcessingException {
        ForismaticDTO mockForismaticDTO = new ForismaticDTO();
        when(forismaticRestClient.getForismaticQuote(anyString())).thenReturn(mockForismaticDTO);
        forismaticRunner.run("Russian");
        verify(forismaticRestClient).getForismaticQuote(anyString());
    }
}