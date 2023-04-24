package com.example.JourneyGenie_01.domain.dto.icBusRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item{
    private String arrPlaceNm;
    private String depPlaceNm;
    private String arrPlandTime;
    private String depPlandTime;
    private String charge;
    private String gradeNm;
}
