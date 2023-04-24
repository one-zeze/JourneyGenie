package com.example.JourneyGenie_01.domain.dto.trainRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item{
    public String depplacename;
    public String arrplacename;
    public String arrplandtime;
    public String depplandtime;
    public int adultcharge;
    public String traingradename;
    public int trainno;
}
