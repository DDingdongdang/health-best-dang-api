package com.healthbest.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthResponse {

    @Getter
    @AllArgsConstructor
    public static class SignUp {
        private Long userId;
    }

    @Getter
    @AllArgsConstructor
    public static class SignIn {
        private Long userId;
        private String token;
    }
}
