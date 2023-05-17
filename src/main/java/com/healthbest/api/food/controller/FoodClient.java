package com.healthbest.api.food.controller;

import com.healthbest.api.food.dto.FoodInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class FoodClient {

    private final FoodProperties properties;

    public FoodInfo request() {
        String path = "/" + properties.getKey()
                + "/" + properties.getServiceId()
                + "/" + properties.getType()
                + "/" + properties.getStartIdx()
                + "/" + properties.getEndIdx();

        WebClient webClient = WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        FoodInfo response = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(FoodInfo.class)
                .block();

        response.getRow().getInfos()
                .forEach(info -> System.out.println(info.toString()));

        return response;
    }
}
