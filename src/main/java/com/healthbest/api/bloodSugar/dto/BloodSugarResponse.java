package com.healthbest.api.bloodSugar.dto;

import com.healthbest.api.bloodSugar.domain.vo.MealTime;
import com.healthbest.api.bloodSugar.domain.vo.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BloodSugarResponse {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long id;
    }

    @Getter
    @AllArgsConstructor
    public static class BloodSugarInfos {
        private List<BloodSugarInfo> infos;
    }

    @Getter
    @AllArgsConstructor
    public static class BloodSugarInfo {
        private MealType mealType;
        private MealTime mealTime;
        private LocalDateTime date;
        private int sugar;
    }
}
