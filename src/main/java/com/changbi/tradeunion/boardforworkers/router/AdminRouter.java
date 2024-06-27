package com.changbi.tradeunion.boardforworkers.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRouter {

    @GetMapping(value = {"", "/", "/index"})
    public String adminIndexPage() {
        return "admin/index";
    }

    @GetMapping("/member/list")
    public String memberListPage(){
        return "admin/member/list";
    }
}
