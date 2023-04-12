package com.healthbest.api.bloodSugar.repository;

import com.healthbest.api.bloodSugar.domain.BloodSugar;
import com.healthbest.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodSugarRepository extends JpaRepository<BloodSugar, Long> {

    @Query("select b from BloodSugar b where b.user = :user and b.date between :startedTime and :endTime order by b.date")
    List<BloodSugar> findBloodSugarByDate(
            @Param("user") User user,
            @Param("startedTime") LocalDateTime startedTime,
            @Param("endTime") LocalDateTime endTime
    );
}
