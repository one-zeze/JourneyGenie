package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.CityEntity;
import com.example.JourneyGenie_01.domain.entity.TrainEntity;
import com.example.JourneyGenie_01.domain.entity.TrainStationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TrainStationRepository trainStationRepository;
    @Autowired
    private TrainRepository trainRepository;

    @Test
    void test(){
//        var city = CityEntity
//                .builder()
//                .cityName("대구")
//                .cityCode("22")
//                .build();
//        cityRepository.save(city);

//        var station = TrainStationEntity
//                .builder()
//                .city(city)
//                .nodeId("NAT013271")
//                .nodeName("동대구")
//                .build();
//        trainStationRepository.save(station);
        var station = trainStationRepository.findById(1L);
        System.out.println(station.get());

//        var train = TrainEntity
//                .builder()
//                .trainStation(station)
//                .trainName("KTX")
//                .trainNum("00")
//                .depPlace("NAT010000")
//                .arrPlace(station.getNodeId())
//                .depTime("202303220020")
//                .arrTime("202303220120")
//                .charge(46500)
//                .build();
//        trainRepository.save(train);

    }
}