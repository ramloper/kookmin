package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.DayOfWeek;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Notification;
import org.kookmin.demo.dto.request.education.EducationSearchDTO;
import org.kookmin.demo.service.DayOfWeekService;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.NotificationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final EducationService educationService;
    private final RentalService rentalService;
    private final DayOfWeekService dayOfWeekService;
    private final NotificationService notificationService;

    @GetMapping("main")
    public String main(Model model, @RequestParam(defaultValue = "0") int page){
        int pageSize = 12; // 페이지당 항목 수
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Education> educationPage = educationService.findAllPage(pageable);

        List<Education> educationList = educationPage.getContent();
        model.addAttribute("educationList", educationList);
        model.addAttribute("educationPage", educationPage);


        DayOfWeek dayOfWeekRental = dayOfWeekService.findByDayOfWeekRental();
        model.addAttribute("dayOfWeekRental", dayOfWeekRental);
        DayOfWeek dayOfWeekReturn = dayOfWeekService.findByDayOfWeekReturn();
        model.addAttribute("dayOfWeekReturn", dayOfWeekReturn);

        List<Notification> notificationList = notificationService.notificationFindAllByToMain();
        model.addAttribute("notificationList", notificationList);

        return "main/main";
    }

    @GetMapping("")
    public String toMain(){
        return "redirect:/main";
    }

    @PostMapping("main/search")
    public String educationSearch(EducationSearchDTO dto,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model){
        int pageSize = 12; // 페이지당 항목 수
        PageRequest pageable = PageRequest.of(page, pageSize);

        Page<Education> educationPage = educationService.findAllSearchType(dto, pageable);

        List<Education> educationList = educationPage.getContent();
        model.addAttribute("educationList", educationList);
        model.addAttribute("educationPage", educationPage);

        return "main/main";
    }
}
