package com.example.JourneyGenie_01.controller;

import com.example.JourneyGenie_01.domain.dto.icBusRes.IcBusRes;
import com.example.JourneyGenie_01.domain.dto.icTerminalRes.IcTerminalRes;
import com.example.JourneyGenie_01.service.IcBusSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ICBusApiController {

    private final IcBusSvc icBusSvc;

    // 터미널 반환, ex) cityName = "서울특별시", "대구광역시", "경상북도"
    @GetMapping("/search_icb")
    public List<String> cityIcTerminal(@RequestParam String cityNameIc, Model model){
        model.addAttribute("icbTerminal", icBusSvc.searchICBTerminalList(cityNameIc));
        return icBusSvc.searchICBTerminalList(cityNameIc);
    }

//    @GetMapping("/bus_info/terminal")
//    public IcTerminalRes terminal(@RequestParam String cityName) {
//        return icBusSvc.SearchIcTerminal(cityName);
//    }

    // 버스정보 반환, ex)
    @GetMapping("/bus_info/accdep")
    public IcBusRes accbus_depbus(@RequestParam String depT, @RequestParam String arrT) {
        return icBusSvc.SearchIcBusInfo(depT, arrT);
    }

    @GetMapping("/bus_info/grade")
    public String busGrade() {
        return icBusSvc.SearchIcBusGrade();
    }
    @GetMapping("/bus_info/citycode")
    public String city_code() {
        return icBusSvc.SearchCity();
    }
}
