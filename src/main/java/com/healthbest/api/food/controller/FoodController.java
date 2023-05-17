package com.healthbest.api.food.controller;

import com.healthbest.api.food.dto.FoodGetRequest;
import com.healthbest.api.food.dto.FoodGetResponse;
import com.healthbest.api.food.dto.FoodInfo;
import com.healthbest.api.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FoodController {

    private final FoodClient foodClient;
    private final FoodService foodService;

    @GetMapping("/foods/open-api")
    public void parsing() {
        FoodInfo foodInfo = foodClient.request();
        foodService.create(foodInfo);
    }

    @GetMapping("/foods/info")
    public ResponseEntity<FoodGetResponse> get(@RequestBody FoodGetRequest foodGetRequest) {
        FoodGetResponse response = foodService.get(foodGetRequest);
        return ResponseEntity.ok(response);
    }
}
