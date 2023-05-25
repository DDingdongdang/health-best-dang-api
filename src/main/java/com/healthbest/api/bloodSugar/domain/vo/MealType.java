package com.healthbest.api.bloodSugar.domain.vo;

public enum MealType {
    BREAKFAST("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    ;

    private String value;

    private MealType(String value) {
        this.value = value;
    }
}
