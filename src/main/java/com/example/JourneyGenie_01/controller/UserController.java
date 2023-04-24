package com.example.JourneyGenie_01.controller;

import com.example.JourneyGenie_01.domain.vo.ReservationVo;
import com.example.JourneyGenie_01.domain.vo.UserVo;
import com.example.JourneyGenie_01.service.ReservateSvc;
import com.example.JourneyGenie_01.service.UserInfoSvc;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserInfoSvc userInfoSvc;
    private final ReservateSvc reservateSvc;
    //회원가입
    @GetMapping("/regist")
    public String regist(@RequestParam String name,
                       @RequestParam String phone,
                       @RequestParam String id,
                       @RequestParam String pwd,
                       @RequestParam String email){
        var result = userInfoSvc.userRegist(name, phone, id, pwd, email);
        //성공여부 출력
        System.out.println("result: "+result);
        return result;
    }

    @GetMapping("/login")
    public String signin(@RequestParam String id, @RequestParam String pwd, HttpSession session){
        var result = userInfoSvc.signIn(id, pwd);

        if (result.equals("등록되지 않은 ID입니다.")){
            return "등록되지 않은 ID입니다.";
        } else if (result.equals("비밀번호가 틀렸습니다.")) {
            return "비밀번호가 틀렸습니다.";
        }else {
            session.setAttribute("userId", result);
            return "로그인 성공";
        }
    }

    //회원정보 출력
    @GetMapping("/getUser")
    public UserVo getUser(@RequestParam String id){
        return userInfoSvc.getUserInfo(id);
    }

    @GetMapping("/findId")
    public String findID(@RequestParam String name, @RequestParam String email){
        return userInfoSvc.findId(name, email);
    }

    @GetMapping("/findPwd")
    public String findPwd(@RequestParam String name, @RequestParam String id, @RequestParam String phone){
        return userInfoSvc.findPwd(name, id, phone);
    }

    //예매하기
    @GetMapping("/reservation")
    public void reservate(@RequestParam String user_id,
                          @RequestParam String info,
                          @RequestParam String depPlace,
                          @RequestParam String arrPlace,
                          @RequestParam String depTime,
                          @RequestParam String arrTime,
                          @RequestParam String charge){

        reservateSvc.reservate(user_id, info, depPlace, arrPlace, depTime, arrTime, charge);
    }

    //예매정보 출력
    @GetMapping("/myReservate")
    public ReservationVo myReservate(@RequestParam String user_id){
        return reservateSvc.myReservation(user_id);
    }
    @GetMapping("/getReservationList")
    public List<ReservationVo> getReservationList(@RequestParam String user_id){
        return reservateSvc.myReservationList(user_id);
    }

    //회원정보 수정
    @GetMapping("/updateInfo")
    public void updateInfo(@RequestParam  String user_id, @RequestParam String new_id,
                           @RequestParam String name, @RequestParam String pwd,
                           @RequestParam String email,@RequestParam String phonenNum){

        log.info("controller called");
        userInfoSvc.updateInfo(user_id, new_id, name, pwd, email, phonenNum);
    }

    //회원정보 삭제
    @GetMapping("/delUser")
    public void delInfo(@RequestParam String user_id){
        userInfoSvc.delUser(user_id);
    }
}
