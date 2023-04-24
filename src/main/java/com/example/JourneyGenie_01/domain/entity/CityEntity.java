package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "city")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long city_id;

    @Column(name = "도시코드")
    private String cityCode;
    @Column(name = "도시명")
    private String cityName;

    @OneToMany(mappedBy = "city")
    @Builder.Default
    @ToString.Exclude
    private List<TrainStationEntity> trainStationEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "city")
    @Builder.Default
    @ToString.Exclude
    private List<IcBusTerminalEntity> icBusTeminalEntityList = new ArrayList<>();

}
