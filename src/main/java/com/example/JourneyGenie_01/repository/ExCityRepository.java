package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.ExCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExCityRepository extends JpaRepository<ExCityEntity, Long> {

    Optional<ExCityEntity> findBycityname(String cityName);
}
