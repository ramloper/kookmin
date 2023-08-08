package org.kookmin.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class ErrorPageController {

    @RequestMapping("error/403")
    public String errorPage403(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 403");
        return "error/403";
    }
    @RequestMapping("error/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 404");
        return "error/404";
    }
    @RequestMapping("error/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 500");
        return "error/500";
    }
}
