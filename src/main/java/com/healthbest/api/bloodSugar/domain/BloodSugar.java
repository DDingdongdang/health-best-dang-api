package com.healthbest.api.bloodSugar.domain;

import com.healthbest.api.bloodSugar.domain.vo.MealTime;
import com.healthbest.api.bloodSugar.domain.vo.MealType;
import com.healthbest.api.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "blood_sugar")
public class BloodSugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int sugar;

    @Enumerated(value = EnumType.STRING)
    private MealType mealType;

    @Enumerated(value = EnumType.STRING)
    private MealTime mealTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public BloodSugar(LocalDateTime date, int sugar, MealType mealType, MealTime mealTime, User user) {
        this.date = date;
        this.sugar = sugar;
        this.mealType = mealType;
        this.mealTime = mealTime;
        this.user = user;
    }
}
