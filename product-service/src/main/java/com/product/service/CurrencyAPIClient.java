package com.product.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CurrencyAPIClient {
    private RestTemplate restTemplate ;

    public Double getPriceForUSD(double usdPrice, Currency currency) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("usdPrice", usdPrice);
        uriVariables.put("convertTo", currency);
        restTemplate = new RestTemplate();
        ResponseEntity<?> responseEntity = restTemplate.exchange(
                "http://localhost:8080/currency/convert/{usdPrice}/{convertTo}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Double>() {},
                uriVariables);
        return (Double) responseEntity.getBody();
    }
}
