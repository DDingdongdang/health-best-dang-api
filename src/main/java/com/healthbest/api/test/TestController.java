package com.healthbest.api.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test2")
    public TestResponseDto test() {
        return new TestResponseDto(1L, "축하축하", "성공이다!!!!!!!!!!!!!!!");
    }
}
