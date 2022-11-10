package com.golablur.server.user.service;

import com.golablur.server.user.domain.UserDTO;
import com.golablur.server.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    UserRepository userRepository;

    public String normalSignup(UserDTO userDTO) {
        // 회원 중에 있는지 확인 있으면 true
        if(userRepository.findUser(userDTO.getUser_ID())) return "202";
        // 없으면 디비 생성
        if(!userRepository.signUp(userDTO)) return null;
        return "200";
    }

    public boolean socialSignup(UserDTO userDTO) {
        return userRepository.signUp(userDTO);
    }
}
