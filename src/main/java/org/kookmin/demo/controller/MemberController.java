package org.kookmin.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/user")
public class MemberController {

    @GetMapping("/login")
    public void login(String error, String logout){
        log.info("login get.......");
        log.info("logout : " + logout);

        if (logout != null) log.info("user logout===========================");
    }
}
