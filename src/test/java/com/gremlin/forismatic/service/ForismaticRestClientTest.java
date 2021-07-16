package com.gremlin.forismatic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gremlin.forismatic.dto.ForismaticDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForismaticRestClientTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ForismaticRestClient forismaticRestClient;

    @Test
    void getForismaticQuote_Returns_ForismaticDTO_Test() throws JsonProcessingException {
        ForismaticDTO expectedDTO = new ForismaticDTO();
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(ForismaticDTO.class))).
                thenReturn(new ResponseEntity<>(expectedDTO, HttpStatus.OK));

        ForismaticDTO actualDTO = forismaticRestClient.getForismaticQuote("en");
        assertEquals(expectedDTO, actualDTO);
    }
}