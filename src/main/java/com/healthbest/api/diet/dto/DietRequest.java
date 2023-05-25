package com.healthbest.api.diet.dto;

import com.healthbest.api.bloodSugar.domain.vo.MealType;
import com.healthbest.api.common.dto.TimeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DietRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private long foodId;
        private int amount;
        private MealType mealType;
        private TimeDto.DateTime time;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {
        private TimeDto.Date date;
    }
}
