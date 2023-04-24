package com.example.JourneyGenie_01.service;

import com.example.JourneyGenie_01.domain.entity.UserEntity;
import com.example.JourneyGenie_01.domain.vo.UserVo;
import com.example.JourneyGenie_01.repository.ReservationRepository;
import com.example.JourneyGenie_01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoSvc {

    private final UserRepository userRepository;
    private final ReservateSvc reservateSvc;

    //회원가입
    public String userRegist(
            String user_name,
            String user_phone,
            String user_id,
            String user_pwd,
            String user_email
    ){
        if (userRepository.findByid(user_id).isPresent()){
            log.info("exist id");
            return "이미 등록된 ID입니다.";
        }else if (userRepository.findByEmail(user_email).isPresent()){
            log.info("exist email");
            return "이미 등록된 Email입니다.";
        }else {
            var newUser = UserEntity.builder()
                    .name(user_name)
                    .phoneNum(user_phone)
                    .id(user_id)
                    .pwd(user_pwd)
                    .email(user_email)
                    .build();
            userRepository.save(newUser);
            log.info("regist complete");
            return "가입완료";
        }
    }

    // 회원정보 출력
    public UserVo getUserInfo(String user_id){
        var userE = userRepository.findByid(user_id).get();

        var userV = UserVo.builder()
                .name(userE.getName())
                .id(userE.getId())
                .pwd(userE.getPwd())
                .email(userE.getEmail())
                .phoneNum(userE.getPhoneNum())
                .build();

        return userV;
    }

    //로그인
    public String signIn(String id, String pwd){
        var user = userRepository.findByid(id);
        //ID 체크
         if (user.isEmpty()){
             return "등록되지 않은 ID입니다.";
         //Password 체크
         }else {
             var user_pwd = user.get().getPwd();
             if (pwd.equals(user_pwd)){
             }else {
                 return "비밀번호가 틀렸습니다.";
             }
         }
        log.info("로그인 성공");
        return user.get().getId();
    }

    // 아이디 찾기
    public String findId(String name, String email){
        var user = userRepository.findBynameAndEmail(name, email).get();
        return user.getId();
    }

    // 비밀번호 찾기
    public String findPwd(String name, String id, String phoneNum){
        var user = userRepository.findBynameAndIdAndPhoneNum(name, id, phoneNum).get();
        return user.getPwd();
    }

    //회원정보 수정
    public void updateInfo(String user_id, String new_id, String name, String pwd, String email, String phonenNum){
        log.info("svc called: updateInfo()");
        var user = userRepository.findByid(user_id).get();
        user.setId(new_id);
        user.setName(name);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setPhoneNum(phonenNum);

        userRepository.save(user);
    }

    //회원정보 삭제
    @Transactional
    public void delUser(String user_id){
        var user = userRepository.findByid(user_id).get();
        reservateSvc.delReservation(user);
        userRepository.deleteById(user.getUser_id());
    }
}
