package com.healthbest.api.bloodSugar.controller;

import com.healthbest.api.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class bloodSugarController {

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User user) {
        log.info("user : {}", user.getId());
        return user.getId() + " " + user.getLoginId() + " " + user.getPassword() + " " + user.getNickName();
    }
}
