package com.example.JourneyGenie_01.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void tmp1(){

        var user = userRepository.findByid("사실산타는").get();
        System.out.println(user);

        user.setEmail("santanonono@noknow");
        user.setName("걱정마우찬아울어도돼");
        user.setPhoneNum("없거든");
//        userRepository.save(user);
        System.out.println(user);

//        var reservation = reservationRepository.findByUser(user);
//        System.out.println(reservation);
    }

}