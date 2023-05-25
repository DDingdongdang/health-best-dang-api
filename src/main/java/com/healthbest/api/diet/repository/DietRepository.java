package com.healthbest.api.diet.repository;

import com.healthbest.api.bloodSugar.domain.BloodSugar;
import com.healthbest.api.diet.domain.Diet;
import com.healthbest.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

    @Query("select b from Diet b " +
            "where b.user = :user " +
            "and b.date between :startedTime and :endTime order by b.date")
    List<Diet> findDietByDate(
            @Param("user") User user,
            @Param("startedTime") LocalDateTime startedTime,
            @Param("endTime") LocalDateTime endTime
    );
}
