package com.healthbest.api.diet.service;

import com.healthbest.api.bloodSugar.domain.BloodSugar;
import com.healthbest.api.bloodSugar.dto.BloodSugarResponse;
import com.healthbest.api.common.utils.TimeUtil;
import com.healthbest.api.diet.domain.Diet;
import com.healthbest.api.diet.dto.DietRequest;
import com.healthbest.api.diet.dto.DietResponse;
import com.healthbest.api.diet.repository.DietRepository;
import com.healthbest.api.food.domain.Food;
import com.healthbest.api.food.repository.FoodRepository;
import com.healthbest.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DietService {
    private final DietRepository dietRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public DietResponse.Create create(DietRequest.Create request, User user) {
        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 음식입니다."));
        Diet diet = dietRepository.save(new Diet(
                request.getAmount(),
                TimeUtil.toLocalDateTime(request.getTime()),
                request.getMealType(),
                user,
                food
        ));

        return new DietResponse.Create(diet.getId());
    }

    @Transactional(readOnly = true)
    public DietResponse.Get get(DietRequest.Get request, User user) {
        LocalDateTime startedTime = TimeUtil.startedTime(request.getDate());
        LocalDateTime endTime = TimeUtil.endTime(request.getDate());

        List<DietResponse.GetInfo> infos = dietRepository.findDietByDate(user, startedTime, endTime)
                .stream()
                .map(this::toDto)
                .toList();

        return new DietResponse.Get(infos);
    }

    private DietResponse.GetInfo toDto(Diet diet) {
        Food food = diet.getFood();
        double carbohydrate = calculateNutrition(diet.getAmount(), food.getAmount(), food.getCarbohydrate());
        double sugars = calculateNutrition(diet.getAmount(), food.getAmount(), food.getSugars());

        return new DietResponse.GetInfo(
                diet.getMealType().name(),
                diet.getDate().getHour(),
                diet.getDate().getMinute(),
                diet.getAmount(),
                food.getName(),
                carbohydrate,
                sugars
        );
    }

    private double calculateNutrition(int amount, int standardAmount, double nutrition) {
        double result = (amount / (double) standardAmount) * nutrition;
        return Math.round(result * 100.0) / 100.0;
    }
}
