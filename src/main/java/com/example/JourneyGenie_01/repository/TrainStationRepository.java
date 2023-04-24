package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.CityEntity;
import com.example.JourneyGenie_01.domain.entity.TrainStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStationEntity, Long> {

    Optional<List<TrainStationEntity>> findBycity(CityEntity city);
    Optional<TrainStationEntity> findBynodeName(String stationName);
}
