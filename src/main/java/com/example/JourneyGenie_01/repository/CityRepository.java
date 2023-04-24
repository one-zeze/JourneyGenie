package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    //findBy{Column 이름}(Column 타입)
    Optional<CityEntity> findBycityName(String cityName);
}
