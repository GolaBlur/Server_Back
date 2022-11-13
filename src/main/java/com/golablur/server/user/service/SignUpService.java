package com.golablur.server.user.service;

import com.golablur.server.user.domain.UserEntity;
import com.golablur.server.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignUpService {

    @Autowired
    private UserMapper userMapper;

    // 일반 유저 회원가입
    public String normalSignup(UserEntity userEntity) {
        if(userMapper.signUp(userEntity.getUser_ID(), userEntity.getUser_Name(), userEntity.getUser_PW()) == 0) {
            log.error("singUp: 회원가입에 실패했습니다.");
            return "500";
        }
        return "200";
    }

    public String normalIDCheck(String id) {
        if(userMapper.findUser(id) != null) {
            log.error("findUser: 해당 유저가 이미 있습니다.");
            return "400";
        }
        return "200";
    }

}
