package com.healthbest.api.auth.dto;

import com.healthbest.api.user.domain.vo.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SignUp {
        private String loginId;
        private String password;
        private String nickname;
        private Gender gender;
        private int age;
        private int height;
        private int weight;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SignIn {
        private String loginId;
        private String password;
    }
}
