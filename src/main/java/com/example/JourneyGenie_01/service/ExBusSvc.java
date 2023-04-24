package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.dto.exBusRes.ExBusRes;
import com.example.JourneyGenie_01.domain.dto.exTerminalRes.ExTerminalRes;
import com.example.JourneyGenie_01.repository.ExBusTerminalRepository;
import com.example.JourneyGenie_01.repository.ExCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class ExBusSvc {

    @Value("${OpenApi.url.ExBusUrl}")
    private String exBusUrl;
    @Value("${OpenApi.key.exbusKey}")
    private String serviceKey;
    private final ExCityRepository cityRepository;
    private final ExBusTerminalRepository busTerminalRepository;

    @Transactional
    public List<String> searchEXBTerminalList(String cityname) {

        var exCityE = cityRepository.findBycityname(cityname).get();
        var exTerminalE = busTerminalRepository.findBycity(exCityE).get();

        List<String> exbTemp = new ArrayList<>();
        exTerminalE.forEach(e -> {
            exbTemp.add(e.getTerminalnm());
        });
        return exbTemp;
    }

//    public ExTerminalRes getTerminalList(String cityName){
//
//        var city = cityRepository.findBycityname(cityName).orElseThrow();
//        var terminal = busTerminalRepository.findBycity(city);
//        var result = new ExTerminalRes();
//
//        terminal.ifPresent(e->{
//            e.forEach(data->{
//                result.itemList.add(data);
//            });
//        });
//        return result;
//    }

    public ExBusRes getBusInfo(String depT, String arrT){
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        var depCode = busTerminalRepository.findByterminalnm(depT).get().getTerminalid();
        var arrCode = busTerminalRepository.findByterminalnm(arrT).get().getTerminalid();

        try {
            StringBuilder urlBuilder = new StringBuilder(exBusUrl+"/getStrtpntAlocFndExpbusInfo");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + serviceKey);
            urlBuilder.append("&_type=json");
            urlBuilder.append("&depTerminalId=").append(depCode); /*출발터미널ID*/
            urlBuilder.append("&arrTerminalId=").append(arrCode); /*도착터미널ID*/
            urlBuilder.append("&depPlandTime=").append(date); /*출발일(YYYYMMDD)*/
            urlBuilder.append("&numOfRows=100");
            URL url = new URL(urlBuilder.toString());

            RestTemplate restTemplate = new RestTemplate();
            var result = restTemplate.getForEntity(url.toURI(), ExBusRes.class).getBody();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getBusGrade(){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(exBusUrl+"/getExpBusGradList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + serviceKey);
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

    public String getCityCode(){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(exBusUrl+"/getCtyCodeList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + serviceKey);
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
