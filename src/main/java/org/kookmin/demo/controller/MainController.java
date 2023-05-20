package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.member.MemberLoginRequestDTO;
import org.kookmin.demo.service.EducationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final EducationService educationService;

    @GetMapping("")
    public String main(Model model){
        List<Education> educationList = educationService.findAllEducation();
        model.addAttribute("educationList", educationList);
        return "main";
    }




    @PutMapping("start")
    public String login(MemberLoginRequestDTO dto){

        return "redirect:main";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("join")
    public String join(){
        return "join";
    }
}
