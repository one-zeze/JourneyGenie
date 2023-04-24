package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "train")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long train_id;

    @Column(name = "차량종류")
    private String trainName;
    @Column(name = "출발지")
    private String depPlace;
    @Column(name = "도착지")
    private String arrPlace;
    @Column(name = "출발시간")
    private String depTime;
    @Column(name = "도착시간")
    private String arrTime;
    @Column(name = "운임료")
    private Integer charge;
    @Column(name = "열차번호")
    private String trainNum;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private TrainStationEntity trainStation;

//    @OneToMany(mappedBy = "train")
//    @Builder.Default
//    @ToString.Exclude
//    private List<ReservationEntity> reservationEntityList = new ArrayList<>();
}
