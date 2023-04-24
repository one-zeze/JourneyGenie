package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.ReservationEntity;
import com.example.JourneyGenie_01.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<Optional<ReservationEntity>> findByUser(UserEntity user_id);
    void deleteAllByUser(UserEntity user_id);
}
