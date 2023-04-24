package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.entity.ReservationEntity;
import com.example.JourneyGenie_01.domain.vo.ReservationVo;
import com.example.JourneyGenie_01.domain.entity.UserEntity;
import com.example.JourneyGenie_01.repository.ReservationRepository;
import com.example.JourneyGenie_01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservateSvc {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    //예매정보 db저장
    public void reservate(String user_id, String info,
                          String depPlace, String arrPlace,
                          String depTime, String arrTime, String charge){

        var user = userRepository.findByid(user_id).get();
        var reservation = ReservationEntity
                .builder()
                .user(user)
                .info(info)
                .depCity(depPlace)
                .arrCity(arrPlace)
                .depTime(depTime)
                .arrTime(arrTime)
                .charge(charge)
                .build();
        reservationRepository.save(reservation);
    }

    //예매정보 출력
    public ReservationVo myReservation(String user_id){
        var user = userRepository.findByid(user_id).get();
//        var reservation = reservationRepository.findByUser(user).get();
        var length = reservationRepository.findByUser(user).size()-1;
        var reservation = reservationRepository.findByUser(user).get(length).get();

        var result = ReservationVo
                .builder()
                .info(reservation.getInfo())
                .depCity(reservation.getDepCity())
                .arrCity(reservation.getArrCity())
                .depTime(reservation.getDepTime())
                .arrTime(reservation.getArrTime())
                .charge(reservation.getCharge())
                .build();

        return result;
    }

    public ArrayList<ReservationVo> myReservationList(String user_id){
        var user = userRepository.findByid(user_id).get();
        var reservation = reservationRepository.findByUser(user);
        var resultList = new ArrayList<ReservationVo>();

        reservation.forEach(item->{
            var result = ReservationVo
                    .builder()
                    .info(item.get().getInfo())
                    .depCity(item.get().getDepCity())
                    .arrCity(item.get().getArrCity())
                    .depTime(item.get().getDepTime())
                    .arrTime(item.get().getArrTime())
                    .charge(item.get().getCharge())
                    .build();
            resultList.add(result);
        });
        return resultList;
    }

    //예매정보 삭제
    public void delReservation(UserEntity user_id){
        reservationRepository.deleteAllByUser(user_id);
    }
}
