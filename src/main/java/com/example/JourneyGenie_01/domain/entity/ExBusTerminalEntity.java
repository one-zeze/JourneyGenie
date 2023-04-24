package com.example.JourneyGenie_01.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exbus_terminal")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExBusTerminalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exbus_terminal_id;

    private String terminalid;
    private String terminalnm;

    @JsonBackReference //직렬화 수행 안함
    @ManyToOne
    @JoinColumn(name = "citycode")
    private ExCityEntity city;
}
