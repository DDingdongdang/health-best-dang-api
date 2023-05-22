package com.healthbest.api.bloodSugar.controller;

import com.healthbest.api.bloodSugar.dto.BloodSugarRequest;
import com.healthbest.api.bloodSugar.dto.BloodSugarResponse;
import com.healthbest.api.bloodSugar.service.BloodSugarService;
import com.healthbest.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BloodSugarController {

    private final BloodSugarService bloodSugarService;

    // TODO: ResponseEntity<> 로 변경
    @PostMapping("/sugars")
    public BloodSugarResponse.Create create(
            @AuthenticationPrincipal User user,
            @RequestBody BloodSugarRequest.Create request
    ) {
        BloodSugarResponse.Create response = bloodSugarService.create(user, request);
        return response;
    }

    @GetMapping("/sugars")
    public BloodSugarResponse.BloodSugarInfos findByDate(
            @AuthenticationPrincipal User user,
            @RequestBody BloodSugarRequest.Find request
    ) {
        BloodSugarResponse.BloodSugarInfos response = bloodSugarService.findByDate(user, request);
        return response;
    }
}

