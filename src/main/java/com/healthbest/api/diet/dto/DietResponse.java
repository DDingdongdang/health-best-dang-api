package com.healthbest.api.diet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class DietResponse {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long id;
    }

    @Getter
    @AllArgsConstructor
    public static class Get {
        private List<GetInfo> infos;
    }

    @Getter
    @AllArgsConstructor
    public static class GetInfo {
        private String mealType;
        private int hour;
        private int minuit;
        private int amount;
        private String foodName;
        private double carbohydrate;  // 탄수화물
        private double sugars;  // 당
    }
}
