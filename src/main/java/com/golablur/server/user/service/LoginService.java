package com.golablur.server.user.service;

import com.golablur.server.user.domain.DuringWorkDTO;
import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    // 일반 유저 로그인
    public String normalLogin(LoginDTO loginDTO) {
        if(userMapper.login(loginDTO.getId(), loginDTO.getPw()) == null) {
            log.error("login: 로그인에 실패했습니다.");
            return "400";
        }
        return "200";

    }

    // 작업 도중 로그인
    public String duringWork(DuringWorkDTO duringWorkDTO) {
        if(userMapper.updateFileData(duringWorkDTO.getId(), duringWorkDTO.getSessionToken())==0) {
            log.error("duringWork: 파일 데이터의 업데이트를 실패했습니다.");
            return "500";
        }
        return "200";

    }

    // 작업 도중 로그인 ( 일반 유저 )
    public String normalLoginDuringWork(DuringWorkDTO duringWorkDTO) {
        if(userMapper.login(duringWorkDTO.getId(), duringWorkDTO.getPw()) == null) {
            log.error("login: 로그인에 실패했습니다.");
            return "400";
        }
        if(userMapper.updateFileData(duringWorkDTO.getId(), duringWorkDTO.getSessionToken()) == 0) {
            log.error("duringWork: 파일 데이터의 업데이트를 실패했습니다.");
            return "500";
        }
        return "200";
    }
}
