package com.healthbest.api.common.utils;

import com.healthbest.api.common.dto.TimeDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime toLocalDateTime(TimeDto.DateTime dto) {
        LocalDateTime localDateTime =  getLocalDateTime(dto);

        if (localDateTime.isAfter(LocalDateTime.now())) {
            throw new RuntimeException("현재 시간보다 미래의 시간 입니다.");
        }

        return localDateTime;
    }

    private static LocalDateTime getLocalDateTime(TimeDto.DateTime dto) {
        return LocalDateTime.of(
                dto.getYear(),
                dto.getMonth(),
                dto.getDay(),
                dto.getHour(),
                dto.getMinute()
        );
    }

    public static LocalDateTime startedTime(TimeDto.Date dto) {
        return LocalDateTime.of(
                dto.getYear(), dto.getMonth(), dto.getDay(), 0, 0);
    }

    public static LocalDateTime endTime(TimeDto.Date dto) {
        return LocalDateTime.of(
                dto.getYear(), dto.getMonth(), dto.getDay(), 23, 59);
    }
}
