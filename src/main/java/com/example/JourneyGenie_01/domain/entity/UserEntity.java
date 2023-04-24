package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String id;
    private String pwd;
    private String email;
    private String phoneNum;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    @ToString.Exclude
    private List<ReservationEntity> reservationEntityList = new ArrayList<>();
}
