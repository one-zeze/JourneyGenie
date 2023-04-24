package com.example.JourneyGenie_01.repository;

import com.example.JourneyGenie_01.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findBynameAndEmail(String name, String email);
    Optional<UserEntity> findBynameAndIdAndPhoneNum(String name, String id, String phone);
    Optional<UserEntity> findByid(String id);
    Optional<UserEntity> findByEmail(String email);
//    Optional<UserEntity> findByidAndPwd(String id, String pwd);
}
