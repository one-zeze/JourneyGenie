package com.example.JourneyGenie_01.domain.dto.exTerminalRes;

import com.example.JourneyGenie_01.domain.entity.ExBusTerminalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExTerminalRes {

    public List<ExBusTerminalEntity> itemList = new ArrayList<>();

//    public void addList(ExBusTerminalEntity terminal){
//        this.itemList.add(terminal);
//    }

}