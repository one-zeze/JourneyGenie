package com.example.JourneyGenie_01.controller;

import com.example.JourneyGenie_01.domain.dto.stationRes.StationRes;
import com.example.JourneyGenie_01.domain.dto.trainRes.TrainRes;
import com.example.JourneyGenie_01.domain.vo.TrainVo;
import com.example.JourneyGenie_01.service.TrainSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrainApiController {

    private final TrainSvc trainSvc;

    @GetMapping("/train/train_info")
    public TrainRes traininfo(@RequestParam String depNode, @RequestParam String arrNode) {
        return trainSvc.SearchTrainInfo(depNode, arrNode);
    }

    @GetMapping("/search_ts")
    public List<String> citystation(@RequestParam String cityName, Model model){
        model.addAttribute("test", trainSvc.SearchStationList(cityName));
        return trainSvc.SearchStationList(cityName);
    }

    @GetMapping("/train/train_type")
    public String traintype(){
        return trainSvc.SearchTrainType();
    }

    @GetMapping("/train/city_num")
    public String citynum(){
        return trainSvc.SearchCity();
    }

}
