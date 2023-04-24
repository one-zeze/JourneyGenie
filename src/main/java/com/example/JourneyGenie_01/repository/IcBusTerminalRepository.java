package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.CityEntity;
import com.example.JourneyGenie_01.domain.entity.IcBusTerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IcBusTerminalRepository extends JpaRepository<IcBusTerminalEntity, Long> {

    Optional<List<IcBusTerminalEntity>> findBycity(CityEntity city);
    Optional<IcBusTerminalEntity> findByterminalNm(String terminalName);
}
