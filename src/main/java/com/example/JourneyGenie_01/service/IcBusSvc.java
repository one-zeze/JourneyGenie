package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.dto.icBusRes.IcBusRes;
import com.example.JourneyGenie_01.domain.dto.icTerminalRes.IcTerminalRes;
import com.example.JourneyGenie_01.domain.dto.trainRes.TrainRes;
import com.example.JourneyGenie_01.repository.CityRepository;
import com.example.JourneyGenie_01.repository.IcBusTerminalRepository;
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
public class IcBusSvc {

    @Value("${OpenApi.url.IcBusUrl}")
    private String IcBusUrl;
    @Value("${OpenApi.key.icbusKey}")
    private String ServiceKey;
    private final IcBusTerminalRepository terminalRepository;
    private final CityRepository cityRepository;

    @Transactional
    public List<String> searchICBTerminalList(String cityName) {

        var icCityE = cityRepository.findBycityName(cityName).get();
        var icTerminalE = terminalRepository.findBycity(icCityE).get();

        List<String> icbTemp = new ArrayList<>();
        icTerminalE.forEach(e -> {
            icbTemp.add(e.getTerminalNm());
        });
        return icbTemp;
    }
    //터미널 조회
    public IcTerminalRes SearchIcTerminal(String cityName){
        var cityCode = cityRepository.findBycityName(cityName).get().getCityCode();

        try {
            StringBuilder urlBuilder = new StringBuilder(IcBusUrl+"/getSuberbsBusTrminlList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&cityCode=").append(cityCode); //도시코드
            urlBuilder.append("&_type=json");
            URL url = new URL(urlBuilder.toString());

            RestTemplate restTemplate = new RestTemplate();
            var result = restTemplate.getForEntity(url.toURI(), IcTerminalRes.class).getBody();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //버스정보 조회
    public IcBusRes SearchIcBusInfo(String depT, String arrT){
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        var depTerminal = terminalRepository.findByterminalNm(depT).get().getTerminalId();
        var arrTerminal = terminalRepository.findByterminalNm(arrT).get().getTerminalId();

        try {
            StringBuilder urlBuilder = new StringBuilder(IcBusUrl+"/getStrtpntAlocFndSuberbsBusInfo");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&_type=json");
            urlBuilder.append("&depTerminalId=").append(depTerminal); //출발터미널 코드
            urlBuilder.append("&arrTerminalId=").append(arrTerminal); //도착터미널 코드
            urlBuilder.append("&depPlandTime=").append(date); //출발날짜
            urlBuilder.append("&numOfRows=100");
            URL url = new URL(urlBuilder.toString());

            var result = new RestTemplate().getForEntity(url.toURI(), IcBusRes.class).getBody();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String SearchIcBusGrade(){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(IcBusUrl+"/getSuberbsBusGradList");
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
    public String SearchCity(){ //키오류
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(IcBusUrl+"/getCtyCodeList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
            urlBuilder.append("&_type=json");
            URL url = new URL(urlBuilder.toString());
            log.info(url.toString());

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
