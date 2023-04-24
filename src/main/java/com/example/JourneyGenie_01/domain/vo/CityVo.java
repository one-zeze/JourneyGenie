package com.example.JourneyGenie_01.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityVo {
    private Long city_id;
    private String cityCode;
    private String cityName;
}
