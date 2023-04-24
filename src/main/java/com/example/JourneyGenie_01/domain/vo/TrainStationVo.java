package com.example.JourneyGenie_01.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrainStationVo {

    private String nodeId;
    private String nodeName;
}
