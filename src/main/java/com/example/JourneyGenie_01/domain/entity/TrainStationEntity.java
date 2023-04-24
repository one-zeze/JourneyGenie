package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "trainStation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrainStationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long station_id;

    @Column(name = "역_ID")
    private String nodeId;
    @Column(name = "역명")
    private String nodeName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "trainStation")
    @Builder.Default
    @ToString.Exclude
    private List<TrainEntity> trainEntityList = new ArrayList<>();
}
