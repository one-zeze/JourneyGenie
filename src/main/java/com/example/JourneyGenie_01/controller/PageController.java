package com.example.JourneyGenie_01.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {

    //main
    @GetMapping("/journeyGenie.com")
    public String journeyGenie(){
        return "main";
    }
    @GetMapping("/main")
    public void main(HttpSession session){
        System.out.println("main session: "+session.getAttribute("userId"));
    }
    
    //시간표조회
    @GetMapping("/search_popup")
    public void search(){}
    @GetMapping("/search_popup2")
    public void search2(){}
    
    //로그인 화면
    @GetMapping("/mypage_login")
    public void mypage_login(){}

    @GetMapping("/mypage_info")
    public void mypage_info(HttpSession session){
        System.out.println("info session: "+session.getAttribute("userId"));
    }

    @GetMapping("/mypage_book")
    public void mypage_book(HttpSession session){
        System.out.println(" book session: "+session.getAttribute("userId"));
    }

    @GetMapping("/mypage_card")
    public void mypage_card(HttpSession session){
        System.out.println("card session: "+session.getAttribute("userId"));
    }

    @GetMapping("/ticket")
    public void ticket(HttpSession session){
        System.out.println("ticket session: "+session.getAttribute("userId"));
    }

    @GetMapping("/schedule")
    public void schedule(HttpSession session){
        System.out.println("schedule session: "+session.getAttribute("userId"));
    }

    @GetMapping("/find_id")
    public void find_id(){}

    @GetMapping("/find_pass")
    public void find_pass(){}

    @GetMapping("/pay")
    public void pay(HttpSession session){
        System.out.println("pay session: "+session.getAttribute("userId"));
    }

    @GetMapping("/join")
    public void join(){}

    @GetMapping("/mypage_info_modify")
    public void modify(HttpSession session){
        System.out.println("pay session: "+session.getAttribute("userId"));
    }

    @GetMapping("/test")
    public String redirect(HttpSession session){
        System.out.println("before invalidate:"+session.getAttribute("userId"));
        session.invalidate();
        System.out.println("세션종료");

        return "redirect:/main";
//        String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
//        return "redirect:"+ referer; // 이전 페이지로 리다이렉트
    }
}
