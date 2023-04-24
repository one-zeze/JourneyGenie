package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.dto.exTerminalRes.ExTerminalRes;
import com.example.JourneyGenie_01.domain.dto.stationRes.StationRes;
import com.example.JourneyGenie_01.service.ExBusSvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExCityRepositoryTest {

    @Autowired
    ExCityRepository exCityRepository;
    @Autowired
    ExBusTerminalRepository busTerminalRepository;
    @Autowired
    TrainStationRepository trainStationRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    ExBusSvc exBusSvc;

    @Test
    @Transactional
    public void tmp1(){

        var city = exCityRepository.findBycityname("서울특별시").orElseThrow();
        var terminal = busTerminalRepository.findBycity(city);

        var result = new ExTerminalRes();

        terminal.ifPresent(e->{
            e.forEach(data->{
                result.itemList.add(data);
            });
        });

        System.out.println(result);
    }

    @Test
    public void tmp2(){
//        System.out.println(busTerminalRepository.findByterminalnm("동서울"));
        var terminal = busTerminalRepository.findByterminalnm("동서울");
        var codee = terminal.get().getTerminalid();

        System.out.println(codee);
    }

    @Test
    public void tmp3(){
        var cityE = cityRepository.findBycityName("서울특별시").orElseThrow();
        var stationE = trainStationRepository.findBycity(cityE);

        List<String> tmp = new ArrayList<>();
        stationE.get().forEach(e->{
//            System.out.println(e.getNodeName());
            tmp.add(e.getNodeName());
        });

        System.out.println(tmp);

//        var stationD = StationRes.builder()
//                .response(stationE)
//                .build();
    }

}