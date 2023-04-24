package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.entity.CityEntity;
import com.example.JourneyGenie_01.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TerminalAndStationSvc {

    private final CityRepository cityRepository;
    private final TrainStationRepository stationRepository;
    private final IcBusTerminalRepository icBusTerminalRepository;

    //도시별 버스터미널, 기차역 출력
    public void TerminalAndStationList(String cityName, String transportType){

        var city = cityRepository.findBycityName(cityName).orElseThrow();

        if (transportType.equals("기차")) {
            var stationList = stationRepository.findBycity(city);
            stationList.ifPresent(e->{
                e.forEach(System.out::println);
            });

        } else if (transportType.equals("시외버스")) {
            var terminalList = icBusTerminalRepository.findBycity(city);
            terminalList.ifPresent(e->{
                e.forEach(System.out::println);
            });

//        } else if (transportType.equals("고속버스")) {
//            var terminalList = exBusTerminalRepository.findBycity(exCity);
//            terminalList.ifPresent(e->{
//                e.forEach(System.out::println);
//            });
        }
    }
}
