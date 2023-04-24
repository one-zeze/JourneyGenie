package com.example.JourneyGenie_01.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "excity")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExCityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citycode;

    private String cityname;

    @JsonManagedReference //직렬화 수행, Collection Type에 사용함 -> 순환참조 방지
    @OneToMany(mappedBy = "city")
    @Builder.Default
    @ToString.Exclude
    private List<ExBusTerminalEntity> exBusTerminalEntityList = new ArrayList<>();
}
