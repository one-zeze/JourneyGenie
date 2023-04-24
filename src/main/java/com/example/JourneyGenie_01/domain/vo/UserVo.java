package com.example.JourneyGenie_01.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserVo {

    private String name;
    private String id;
    private String pwd;
    private String email;
    private String phoneNum;

    @Override
    public String toString() {
        return "나의 기본정보{\n" +
                "회원명:" + name + '\n' +
                "아이디:" + id + '\n' +
                "비밀번호:" + pwd + '\n' +
                "이메일:" + email + '\n' +
                "휴대폰번호:" + phoneNum + '\n' +
                '}';
    }
}
