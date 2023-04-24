package com.example.JourneyGenie_01.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationVo {

    private String depCity;
    private String arrCity;
    private String depTime;
    private String arrTime;
    private String info;
    private String charge;

}
