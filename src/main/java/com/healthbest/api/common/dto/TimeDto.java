package com.healthbest.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TimeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DateTime {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minuit;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Date {
        private int year;
        private int month;
        private int day;
    }
}
