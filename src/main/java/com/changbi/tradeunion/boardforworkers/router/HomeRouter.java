package com.changbi.tradeunion.boardforworkers.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeRouter {

    @GetMapping("/")
    public String indexPage(){
        return "contents/index";
    }

    @GetMapping("/about")
    public String aboutPage(){
        return "contents/about";
    }

    @GetMapping("/complaint")
    public String complaintPage(){
        return "contents/complaint/index";
    }

    @GetMapping("/global-error")
    public String errorPage() throws Exception{

        throw new Exception("Global Error Handler Test");
    }

}
