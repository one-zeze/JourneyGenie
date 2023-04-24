package com.example.JourneyGenie_01.controller;

import com.example.JourneyGenie_01.domain.dto.exBusRes.ExBusRes;
import com.example.JourneyGenie_01.domain.dto.exTerminalRes.ExTerminalRes;
import com.example.JourneyGenie_01.service.ExBusSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EBApiController {

    private final ExBusSvc exBusSvc;

    //터미널 조회, ex) cityName = "서울", "대전", "천안"
//    @GetMapping("/EB/Expbus_TrminlList")
//    public ExTerminalRes ExpbusTrminlList(@RequestParam String cityName) {
//        return exBusSvc.getTerminalList(cityName);
//    }
    @GetMapping("/search_exb")
    public List<String> cityEXBTerminal(@RequestParam String cityNameT, Model model){
        model.addAttribute("exbTerminal", exBusSvc.searchEXBTerminalList(cityNameT));
        return exBusSvc.searchEXBTerminalList(cityNameT);
    }

    //고속버스 조회, ex) depT = "동서울", arrT = "동대구"
    @GetMapping("/EB/Expbus_Info")
    public ExBusRes ExpbusInfo(@RequestParam String depT, @RequestParam String arrT) {
        return exBusSvc.getBusInfo(depT, arrT);
    }

    @GetMapping("/EB/Expbus_GradList")
    public String ExpbusGradList() {
        return exBusSvc.getBusGrade();
    }

    @GetMapping("/EB/tCtyCode")
    public String CtyCode() {
        return exBusSvc.getCityCode();
    }


}
