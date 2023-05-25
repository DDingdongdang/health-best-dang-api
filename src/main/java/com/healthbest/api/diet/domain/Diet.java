package com.healthbest.api.diet.domain;

import com.healthbest.api.bloodSugar.domain.vo.MealType;
import com.healthbest.api.common.domain.BaseTimeEntity;
import com.healthbest.api.food.domain.Food;
import com.healthbest.api.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diet")
public class Diet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private MealType mealType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public Diet(int amount, LocalDateTime date, User user, Food food) {
        this.amount = amount;
        this.date = date;
        this.user = user;
        this.food = food;
    }
}
