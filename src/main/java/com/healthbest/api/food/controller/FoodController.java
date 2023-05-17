package com.healthbest.api.food.controller;

import com.healthbest.api.food.dto.FoodInfo;
import com.healthbest.api.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FoodController {

    private final FoodClient foodClient;
    private final FoodService foodService;

    @PostMapping("/open-api/foods")
    public void parsing() {
        FoodInfo foodInfo = foodClient.request();
        foodService.create(foodInfo);
    }
}
