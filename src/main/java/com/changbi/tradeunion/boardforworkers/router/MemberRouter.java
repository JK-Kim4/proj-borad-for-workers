package com.changbi.tradeunion.boardforworkers.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberRouter {

    /*로그인 페이지 이동*/
    @GetMapping("/sign-in")
    public String signInPage(){
        return "contents/member/sign-in";
    }

    /*회원가입 페이지 이동*/
    @GetMapping("/sign-up")
    public String signUpPage(){
        return "contents/member/sign-up";
    }
}
