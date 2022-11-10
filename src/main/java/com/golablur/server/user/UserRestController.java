package com.golablur.server.user;

import com.golablur.server.user.domain.DuringWorkDTO;
import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.domain.UserDTO;
import com.golablur.server.user.service.LoginService;
import com.golablur.server.user.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    LoginService loginService;
    @Autowired
    SignUpService signUpService;

    @RequestMapping("/normal/login")
    public String normalLogin(LoginDTO loginDTO) {
        // DB에 유저 정보 확인후 성공 시 User_ID 반환
        if (!loginService.normalLogin(loginDTO)) return "500";
        return "200";
    }

    @RequestMapping("/normal/signup")
    public String normalSignup(UserDTO userDTO) {
        // DB에 유저 정보 저장후 성공 시 User_ID 반환
        String code = signUpService.normalSignup(userDTO);
        if(code == null) return "500";
        // 이미 회원 ID가 있을 경우
        else if(code.equals("202")) return "202";
        return "200";
    }

    @RequestMapping("/social/signup")
    public String socialSignup(UserDTO userDTO){
        // DB에 유저 정보 저장
        // pw는 null 값으로 들어감
        if(!signUpService.socialSignup(userDTO)) return "500";
        return "200";
    }


    // 작업 도중 로그인을 했을 경유
    // 작업하던 토큰값의 데이터를 로그인한 토큰값의 데이터와 합쳐줘야함.
    // 작업하던 토큰값의 DB 에서의 File 데이터를 로그인한 토큰값의 데이터로 리스트를 만들어 둔 뒤
    // 작업하던 토큰값의 데이터를 삭제하고 만들어둔 리스트를 로그인한 토큰값의 데이터로 저장해준다.
    // User_ID 속성값만 바꾸어 저장한다.

    @RequestMapping("/social/login/duringWork")
    public String socialLoginDuringWork(DuringWorkDTO duringWorkDTO){
        // 데이터 변환
        if(!loginService.duringWork(duringWorkDTO)) return "500";
        return "200";
    }

    @RequestMapping("/normal/login/duringWork")
    public String normalLoginDuringWork(DuringWorkDTO duringWorkDTO) {
        // 로그인
        if(!loginService.normalLogin(new LoginDTO(duringWorkDTO.getUser_ID(), duringWorkDTO.getUser_PW()))) return "500";
        // 데이터 변환
        if(!loginService.duringWork(duringWorkDTO)) return "500";
        return "200";
    }

}
