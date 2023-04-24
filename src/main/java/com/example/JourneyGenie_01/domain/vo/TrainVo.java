package com.example.JourneyGenie_01.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrainVo {

    private String trainName; //차량종류명
    private String depTime; //출발시간
    private String arrTime; //도착시간
    private Integer charge; //운임료
    private String trainNum; //열차번호

    @Override
    public String toString() {
        return "기차조회{\n" +
                "열차:" + trainName + '\n' +
                "출발:" + depTime + '\n' +
                "도착:" + arrTime + '\n' +
                "운임료:" + charge +
                "열차번호:" + trainNum + '\n' +
                '}';
    }
}
