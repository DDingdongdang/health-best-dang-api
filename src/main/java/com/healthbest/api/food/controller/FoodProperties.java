package com.healthbest.api.food.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FoodProperties {
    @Value("${open-api.service-id}")
    private String serviceId;

    @Value("${open-api.base-url}")
    private String baseUrl;

    @Value("${open-api.key}")
    private String key;

    private final String type = "json";
    private final int startIdx = 1;
    private final int endIdx = 10;   /* 인덱스 하나 당 음식 하나 */
}
