package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "bus")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bus_id;

    @Column(name = "버스등급")
    private String busGrade; //버스등급
    @Column(name = "출발지")
    private String depPlace; //출발지
    @Column(name = "도착지")
    private String arrPlace; //도착지
    @Column(name = "출발시간")
    private String depTime; //출발시간
    @Column(name = "도착시간")
    private String arrTime; //도착시간
    @Column(name = "운임료")
    private String charge; //운임료

    @ManyToOne
    @JoinColumn(name = "icbus_terminal_id")
    private IcBusTerminalEntity icBusTerminal;

//    @ManyToOne
//    @JoinColumn(name = "exbus_terminal_id")
//    private ExBusTerminalEntity exBusTerminal;

//    @OneToMany(mappedBy = "bus")
//    @Builder.Default
//    @ToString.Exclude
//    private List<ReservationEntity> reservationEntityList = new ArrayList<>();
}
