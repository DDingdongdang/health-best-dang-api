package com.healthbest.api.bloodSugar.domain.vo;

public enum MealTime {
    BEFORE_MEAL("식전"),
    AFTER_MEAL("식후"),
    ;

    private String value;

    private MealTime(String value) {
        this.value = value;
    }
}
