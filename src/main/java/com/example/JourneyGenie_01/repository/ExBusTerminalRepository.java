package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.ExBusTerminalEntity;
import com.example.JourneyGenie_01.domain.entity.ExCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExBusTerminalRepository extends JpaRepository<ExBusTerminalEntity, Long> {

    Optional<List<ExBusTerminalEntity>> findBycity(ExCityEntity city);


    Optional<ExBusTerminalEntity> findByterminalnm(String name);
}
