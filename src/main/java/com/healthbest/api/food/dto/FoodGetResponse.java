package com.healthbest.api.food.dto;

import com.healthbest.api.food.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodGetResponse {
    private String name;

    private int amount;

    private double calorie;

    private double carbohydrate;

    private double protein;

    private double fat;

    private double sugars;

    private double sodium;

    public static FoodGetResponse generate(Food food) {
        return new FoodGetResponse(
                food.getName(),
                food.getAmount(),
                food.getCalorie(),
                food.getCarbohydrate(),
                food.getProtein(),
                food.getFat(),
                food.getSugars(),
                food.getSodium()
        );
    }
}
