package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @Column(name = "출발지")
    private String depCity;
    @Column(name = "도착지")
    private String arrCity;
    @Column(name = "출발시간")
    private String depTime;
    @Column(name = "도착시간")
    private String arrTime;
    @Column(name = "금액")
    private String charge;
    private String info;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

//    @ManyToOne
//    @JoinColumn(name = "bus_id")
//    private BusEntity bus;
//
//    @ManyToOne
//    @JoinColumn(name = "train_id")
//    private TrainEntity train;
}
