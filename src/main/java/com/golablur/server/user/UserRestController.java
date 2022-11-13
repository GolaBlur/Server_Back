package com.golablur.server.user;

import com.golablur.server.user.domain.DuringWorkDTO;
import com.golablur.server.user.domain.LoginDTO;
import com.golablur.server.user.domain.UserEntity;
import com.golablur.server.user.service.LoginService;
import com.golablur.server.user.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {

    LoginService loginService;
    SignUpService signUpService;

    @Autowired
    public UserRestController(SignUpService signUpService, LoginService loginService) {
        this.loginService = loginService;
        this.signUpService = signUpService;
    }


    @RequestMapping("/normal/login")
    public String normalLogin(LoginDTO loginDTO) {
        return loginService.normalLogin(loginDTO);
    }

    @RequestMapping("/signup")
    public String normalSignup(UserEntity userEntity) {
        return signUpService.normalSignup(userEntity);
    }



    // 작업 도중 로그인을 했을 경유
    // 작업하던 토큰값의 데이터를 로그인한 토큰값의 데이터와 합쳐줘야함.
    // 작업하던 토큰값의 DB 에서의 File 데이터를 로그인한 토큰값의 데이터로 리스트를 만들어 둔 뒤
    // 작업하던 토큰값의 데이터를 삭제하고 만들어둔 리스트를 로그인한 토큰값의 데이터로 저장해준다.
    // User_ID 속성값만 바꾸어 저장한다.

    @RequestMapping("/social/login/duringWork")
    public String socialLoginDuringWork(DuringWorkDTO duringWorkDTO){
        return loginService.duringWork(duringWorkDTO);
    }

    @RequestMapping("/normal/login/duringWork")
    public String normalLoginDuringWork(DuringWorkDTO duringWorkDTO) {
        return loginService.normalLoginDuringWork(duringWorkDTO);
    }

}
