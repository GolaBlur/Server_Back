package com.golablur.server.user.service;

import com.golablur.server.file.overall.domain.FileDTO;
import com.golablur.server.user.domain.DuringWorkDTO;
import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginService {

    @Autowired
    UserRepository userRepository;

    // 일반 유저 로그인
    public boolean normalLogin(LoginDTO loginDTO) {
        return userRepository.login(loginDTO);
    }

    // 작업 도중 로그인 시 데이터 변환
    // 기존 sessionToken 값을 UserID로 갖는 row의 데이터들을 모두 리스트에 저장한 뒤
    // 해당 row들을 삭제하고 리스트에 저장한 데이터들의 UserID를 로그인한 ID로 바꿔주어 다시 저장한다.
    public boolean duringWork(DuringWorkDTO duringWorkDTO) {
        List rowData = userRepository.getRowData(duringWorkDTO.getSessionToken());
        log.info("rowData: " + rowData);
        if(rowData == null) return false;
        transData(duringWorkDTO.getUser_ID(), rowData);
        if(!userRepository.removeRowData(duringWorkDTO.getSessionToken())) return false;
        if(!userRepository.updateRowData(duringWorkDTO.getUser_ID(), rowData)) return false;
        return true;
    }

    public void transData(String User_ID, List rowData){
        for(Object obj : rowData){
            FileDTO fileDTO = (FileDTO) obj;
            fileDTO.setUser_ID(User_ID);
        }
    }

}
