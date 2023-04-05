package com.healthbest.api.user.domain;

import com.healthbest.api.common.BaseTimeEntity;
import com.healthbest.api.user.domain.vo.Authority;
import com.healthbest.api.user.domain.vo.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "user")
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    private String nickName;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private int age;

    private int height;

    private int weight;

    @Builder
    public User(String loginId, String password, String nickName, Gender gender, int age, int height, int weight) {
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}
