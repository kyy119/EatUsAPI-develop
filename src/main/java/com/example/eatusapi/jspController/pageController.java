package com.example.eatusapi.jspController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class pageController {

    @GetMapping("/main")
    public String jspMain(){
        return "main";
    }

    @GetMapping("/join")
    public String jspJoin(){
        return "join";
    }

    @GetMapping("/login")
    public String jspLogin(){
        return "login";
    }
}
