package com.healthbest.api.domain;

import com.healthbest.api.user.domain.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class BloodSugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private int sugar;
}
