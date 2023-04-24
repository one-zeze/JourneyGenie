package com.example.JourneyGenie_01.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "icbus_terminal")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IcBusTerminalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long icbus_terminal_id;

    @Column(name = "터미널코드")
    private String terminalId;
    @Column(name = "터미널명")
    private String terminalNm;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "icBusTerminal")
    @Builder.Default
    @ToString.Exclude
    private List<BusEntity> busEntityList = new ArrayList<>();
}
