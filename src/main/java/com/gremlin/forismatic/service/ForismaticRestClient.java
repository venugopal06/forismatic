package com.gremlin.forismatic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gremlin.forismatic.dto.ForismaticDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@AllArgsConstructor
public class ForismaticRestClient {

    private final RestTemplate restTemplate;

    private final static String FORISMATIC_API = "http://api.forismatic.com/api/1.0/";

    private final static String METHOD = "method";
    private final static String FORMAT = "format";
    private final static String LANG = "lang";
    private final static String METHOD_VALUE = "getQuote";
    private final static String FORMAT_JSON = "json";

    /**
     * This method interacts with the forismatic api (GET) to retrieve the quote details in the user requested language
     * @param languageCode, the language in which to retrieve the quotes
     * @return the POJO representing the quote details from the service
     * @throws JsonProcessingException, will be thrown in case of errors during unmarshalling of the api response
     */
    public ForismaticDTO getForismaticQuote(String languageCode) throws JsonProcessingException {
        // Initializing the http headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("user-agent", "");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(httpHeaders);

        // Setting up the query parameters
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(FORISMATIC_API)
                .queryParam(METHOD, METHOD_VALUE)
                .queryParam(FORMAT, FORMAT_JSON)
                .queryParam(LANG, languageCode);
        log.info("Hitting the forismatic api with the language code: " + languageCode);
        ResponseEntity<ForismaticDTO> response = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, requestEntity, ForismaticDTO.class);

        return response.getBody();
    }
}
