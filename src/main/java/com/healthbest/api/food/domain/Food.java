package com.healthbest.api.food.domain;

import com.healthbest.api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "food")
public class Food extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int amount;

    private double calorie;

    private double carbohydrate;

    private double protein;

    private double fat;

    private double sugars;

    private double sodium;

    @Builder
    public Food(
            String name, int amount, double calorie, double carbohydrate,
            double protein, double fat, double sugars, double sodium
    ) {
        this.name = name;
        this.amount = amount;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugars = sugars;
        this.sodium = sodium;
    }
}
