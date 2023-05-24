package com.healthbest.api.test;

import com.healthbest.api.common.dto.TimeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test2")
    public TestResponseDto test() {
        return new TestResponseDto(1L, "축하축하", "성공이다!!!!!!!!!!!!!!!", LocalDateTime.now());
    }
}
