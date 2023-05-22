package com.healthbest.api.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class TestResponseDto {
    private long id;
    private String title;
    private String content;
}
