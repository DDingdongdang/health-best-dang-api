package com.healthbest.api.diet.controller;

import com.healthbest.api.diet.dto.DietRequest;
import com.healthbest.api.diet.dto.DietResponse;
import com.healthbest.api.diet.service.DietService;
import com.healthbest.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DietController {
    private final DietService dietService;

    @PostMapping("/diets")
    public ResponseEntity<DietResponse.Create> create(
            @AuthenticationPrincipal User user,
            @RequestBody DietRequest.Create request
    ) {
        DietResponse.Create response = dietService.create(request, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/diets")
    public ResponseEntity<DietResponse.Get> findByDate(
            @AuthenticationPrincipal User user,
            @RequestBody DietRequest.Get request
    ) {
        DietResponse.Get response = dietService.get(request, user);
        return ResponseEntity.ok(response);
    }
}
