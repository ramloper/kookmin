package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.education.EducationSearchDTO;
import org.kookmin.demo.dto.request.member.MemberLoginDTO;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String main(Model model, @RequestParam(defaultValue = "0") int page){
        int pageSize = 10; // 페이지당 항목 수
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Education> educationPage = educationService.findAllPage(pageable);

        List<Education> educationList = educationPage.getContent();
        model.addAttribute("educationList", educationList);
        model.addAttribute("educationPage", educationPage);
        LocalDate nextThursday = rentalService.returnDay();
        model.addAttribute("nextThursday", nextThursday);
        return "main";
    }

    @PostMapping("/search")
    public String educationSearch(EducationSearchDTO dto,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model){
        System.out.println("=================");
        System.out.println(dto.getSearchType());
        System.out.println(dto.getName());
        int pageSize = 10; // 페이지당 항목 수
        PageRequest pageable = PageRequest.of(page, pageSize);

        Page<Education> educationPage = educationService.findAllSearchType(dto, pageable);

        List<Education> educationList = educationPage.getContent();
        model.addAttribute("educationList", educationList);
        model.addAttribute("educationPage", educationPage);
        LocalDate nextThursday = rentalService.returnDay();
        model.addAttribute("nextThursday", nextThursday);
        return "main";
    }


    @PutMapping("start")
    public String login(MemberLoginDTO dto){

        return "redirect:main";
    }
}
