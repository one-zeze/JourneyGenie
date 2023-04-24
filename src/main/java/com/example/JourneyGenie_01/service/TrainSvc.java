package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.dto.stationRes.StationRes;
import com.example.JourneyGenie_01.domain.dto.trainRes.TrainRes;
import com.example.JourneyGenie_01.repository.CityRepository;
import com.example.JourneyGenie_01.repository.TrainStationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainSvc {
    private final CityRepository cityRepository;
    private final TrainStationRepository stationRepository;

    @Value("${OpenApi.key.trainKey}")
    private String ServiceKey;
    @Value("${OpenApi.url.TrainUrl}")
    private String TrainUrl;

    // 기차조회 param:출발 기차역, 도착 기차역
    public TrainRes SearchTrainInfo(String depNode, String arrNode){
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        var depStation = stationRepository.findBynodeName(depNode).orElseThrow();
        var arrStation = stationRepository.findBynodeName(arrNode).orElseThrow();

        try {
            StringBuilder urlBuilder = new StringBuilder(TrainUrl+"/getStrtpntAlocFndTrainInfo");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&_type=json");
            urlBuilder.append("&depPlaceId=").append(depStation.getNodeId()); //출발역 ID
            urlBuilder.append("&arrPlaceId=").append(arrStation.getNodeId()); //도착역 ID
            urlBuilder.append("&depPlandTime=").append(date); //출발일(YYMMDD)
            urlBuilder.append("&numOfRows=100");
            URL url = new URL(urlBuilder.toString());

            RestTemplate restTemplate = new RestTemplate();
            var result = restTemplate.getForEntity(url.toURI(), TrainRes.class).getBody();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 기차역 조회 param:도시이름(ex: 서울특별시, 대구광역시, 경상남도 etc)
    @Transactional
    public List<String> SearchStationList(String cityName){

        var cityE = cityRepository.findBycityName(cityName).get();
        var stationE = stationRepository.findBycity(cityE).get();

        List<String> tmp = new ArrayList<>();
        stationE.forEach(e->{
            tmp.add(e.getNodeName());
        });
        return tmp;

//        try {
//            StringBuilder urlBuilder = new StringBuilder(TrainUrl+"/getCtyAcctoTrainSttnList");
//            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
//            urlBuilder.append("&_type=json");
//            urlBuilder.append("&cityCode=").append(cityE.getCityCode());
//            urlBuilder.append("&numOfRows=15");
//            URL url = new URL(urlBuilder.toString());
//
//            RestTemplate restTemplate = new RestTemplate();
//            var result = restTemplate.getForEntity(url.toURI(), StationRes.class).getBody();
//            return result;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public String SearchTrainType(){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(TrainUrl+"/getVhcleKndList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&_type=json");
            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }
    public String SearchCity(){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(TrainUrl+"/getCtyCodeList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&_type=json");
            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }
}
