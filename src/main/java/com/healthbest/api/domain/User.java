package com.healthbest.api.domain;

import com.healthbest.api.common.BaseTimeEntity;
import com.healthbest.api.domain.vo.Gender;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickName;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private int age;

    private int height;

    private int weight;
}
