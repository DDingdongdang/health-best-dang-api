package com.healthbest.api.bloodSugar.service;

import com.healthbest.api.bloodSugar.domain.BloodSugar;
import com.healthbest.api.bloodSugar.dto.BloodSugarRequest;
import com.healthbest.api.bloodSugar.repository.BloodSugarRepository;
import com.healthbest.api.common.dto.TimeDto;
import com.healthbest.api.common.utils.TimeUtil;
import com.healthbest.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.healthbest.api.bloodSugar.dto.BloodSugarResponse.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BloodSugarService {

    private final BloodSugarRepository bloodSugarRepository;

    @Transactional
    public Create create(User user, BloodSugarRequest.Create request) {
        LocalDateTime date = toLocalDateTime(request.getTime());
        BloodSugar bloodSugar = bloodSugarRepository.save(BloodSugar.builder()
                .date(date)
                .sugar(request.getSugar())
                .mealTime(request.getMealTime())
                .mealType(request.getMealType())
                .user(user)
                .build()
        );

        return new Create(bloodSugar.getId());
    }

    private LocalDateTime toLocalDateTime(TimeDto.DateTime timeDto) {
        LocalDateTime date = TimeUtil.toLocalDateTime(timeDto);

        if (date.isAfter(LocalDateTime.now())) {
            throw new RuntimeException("현재 시간보다 미래의 시간 입니다.");
        }
        return date;
    }

    @Transactional(readOnly = true)
    public BloodSugarInfos findByDate(User user, BloodSugarRequest.Find request) {
        LocalDateTime startedTime = TimeUtil.startedTime(request.getDate());
        LocalDateTime endTime = TimeUtil.endTime(request.getDate());
        log.info("startedTime : {}", startedTime);
        log.info("endTime : {}", endTime);

        List<BloodSugarInfo> infos = bloodSugarRepository.findBloodSugarByDate(user, startedTime, endTime)
                .stream()
                .map(this::toDto)
                .toList();

        return new BloodSugarInfos(infos);
    }

    private BloodSugarInfo toDto(BloodSugar bloodSugar) {
        return new BloodSugarInfo(
                bloodSugar.getMealType(),
                bloodSugar.getMealTime(),
                bloodSugar.getDate(),
                bloodSugar.getSugar()
        );
    }
}
