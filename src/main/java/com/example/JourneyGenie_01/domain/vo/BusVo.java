package com.example.JourneyGenie_01.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BusVo {

    private String busGrade; //버스등급
    private String depPlace; //출발지
    private String arrPlace; //도착지
    private String depTime; //출발시간
    private String arrTime; //도착시간
    private String charge; //운임료
}
