package com.healthbest.api.food.service;

import com.healthbest.api.food.domain.Food;
import com.healthbest.api.food.dto.FoodGetRequest;
import com.healthbest.api.food.dto.FoodGetResponse;
import com.healthbest.api.food.dto.FoodInfo;
import com.healthbest.api.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public void create(FoodInfo foodInfo) {
        foodInfo.getRow().getInfos().stream()
                .map(this::generate)
                .forEach(foodRepository::save);
    }

    private Food generate(FoodInfo.Info info) {
        return Food.builder()
                .name(info.getName())
                .amount(Integer.parseInt(info.getSize()))
                .calorie(info.getCalorie())
                .carbohydrate(info.getCarbohydrate())
                .protein(info.getProtein())
                .fat(info.getFat())
                .sugars(info.getSugars())
                .sodium(info.getSodium())
                .build();
    }

    @Transactional(readOnly = true)
    public FoodGetResponse get(FoodGetRequest request) {
        Food food = foodRepository.findByName(request.getName())
                .orElseThrow(() -> new RuntimeException("해당 음식에 대한 영양 정보가 존재하지 않습니다."));
        return FoodGetResponse.generate(food);
    }
}
