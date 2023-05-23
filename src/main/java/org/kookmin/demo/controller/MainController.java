package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.member.MemberLoginDTO;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final EducationService educationService;
    private final RentalService rentalService;

    @GetMapping("")
    public String main(Model model){
        List<Education> educationList = educationService.findAllEducation();
        LocalDate nextThursday = rentalService.returnDay();

        model.addAttribute("nextThursday", nextThursday);
        model.addAttribute("educationList", educationList);
        return "main";
    }


    @PutMapping("start")
    public String login(MemberLoginDTO dto){

        return "redirect:main";
    }
}
