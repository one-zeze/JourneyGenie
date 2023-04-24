package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.entity.ReservationEntity;
import com.example.JourneyGenie_01.domain.entity.UserEntity;
import com.example.JourneyGenie_01.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservateSvcTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TrainStationRepository stationRepository;
    @Autowired
    IcBusTerminalRepository icBusTerminalRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservateSvc reservateSvc;
    @Autowired
    TerminalAndStationSvc terminalAndStationSvc;
    @Autowired
    TrainSvc trainSvc;
    @Autowired
    IcBusSvc icBusSvc;
    @Autowired
    UserInfoSvc userInfoSvc;

    // 예매정보 출력 테스트
//    @Test
//    @Rollback(value = false)
//    @Transactional
//    public void tmp(){
//        var user = userRepository.findById(1L).orElseThrow();
//
//        reservateSvc.reservate("user");
//    }

//    @Test
//    @Transactional
//    public void TerminalAndStationListInfo(){
//        //사용자
//        var user = userRepository.findById(1L).orElseThrow();
//
//        //출발,도착지
//        var depCity = cityRepository.findBycityName("울산광역시").orElseThrow();
//        var arrCity = cityRepository.findBycityName("대구광역시").orElseThrow();
//
//        //기차역
//        var depStationList = stationRepository.findBycity(depCity);
//        var arrStationList = stationRepository.findBycity(arrCity);
//
//        //터미널
//        var depTerminal = icBusTerminalRepository.findBycity(depCity);
//        var arrTerminal = icBusTerminalRepository.findBycity(arrCity);
//
//        System.out.println("user:"+user);
//        System.out.println("출발지:"+depCity+" ,도착지:"+arrCity);
//        System.out.println("출발기차역:"+depStationList);
//        System.out.println("도착기차역:"+arrStationList);
//        System.out.println(trainSvc.SearchTrainInfo("NAT750726", "NAT013271");
//
//        System.out.println("----------------------------------------------");
//
//        System.out.println("출발지:"+depCity+" ,도착지:"+arrCity);
//        System.out.println("출발기차역:"+depTerminal);
//        System.out.println("도착기차역:"+arrTerminal);
//        System.out.println(icBusSvc.SearchIcBusInfo("NAI0671801", "NAI3214401", "20230325"));
//
//    }

    @Test //기차정보 Dto로 반환 테스트
    public void tmp3(){
//        System.out.println(userInfoSvc.getUserInfo(1L));
//        System.out.println(trainSvc.SearchStationList("세종특별시"));
//        System.out.println(trainSvc.SearchStationList("서울특별시"));
//        System.out.println(trainSvc.SearchStationList("대구광역시"));

        var result = trainSvc.SearchTrainInfo("서울", "동대구");
        System.out.println(result);
        System.out.println(result.getResponse());
        System.out.println(result.getResponse().getBody());
        System.out.println(result.getResponse().getBody().getItems());
        System.out.println(result.getResponse().getBody().getItems().getItem());
    }

//    @Test //기차역정보 Dto로 반환 테스트
//    public void tmp4(){
//        var result  = trainSvc.SearchStationList("서울특별시");
//        System.out.println(result);
//        System.out.println(result.getResponse());
//        System.out.println(result.getResponse().getBody());
//        System.out.println(result.getResponse().getBody().getItems());
//        System.out.println(result.getResponse().getBody().getItems().getItem());
//    }
    
    @Test //고속버스터미널 조회
    public void tmp5(){
        terminalAndStationSvc.TerminalAndStationList("서울특별시", "고속버스");
        terminalAndStationSvc.TerminalAndStationList("서울특별시", "시외버스");
        terminalAndStationSvc.TerminalAndStationList("서울특별시", "기차");
    }
}