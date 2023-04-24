package com.example.JourneyGenie_01.domain.dto.exBusRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Body{
    public Items items;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}
