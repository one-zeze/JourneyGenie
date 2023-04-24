package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long> {
}
