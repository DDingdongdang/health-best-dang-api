package com.healthbest.api.bloodSugar.dto;

import com.healthbest.api.bloodSugar.domain.vo.MealTime;
import com.healthbest.api.bloodSugar.domain.vo.MealType;
import com.healthbest.api.common.dto.TimeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class BloodSugarRequest {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private TimeDto.DateTime time;
        private MealType mealType;
        private MealTime mealTime;
        private int sugar;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find {
        private TimeDto.Date date;
    }
}
