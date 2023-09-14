package org.kookmin.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kookmin.demo.domain.DayOfWeek;
import org.kookmin.demo.domain.Notification;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.education.EducationDaySaveDTO;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.dto.request.notification.NotificationRequestDTO;
import org.kookmin.demo.service.DayOfWeekService;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.NotificationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PropertySource("classpath:/application-dev.properties")
@Slf4j
public class AdminController {
    private final EducationService educationService;
    private final RentalService rentalService;
    private final DayOfWeekService dayOfWeekService;
    private final NotificationService notificationService;



    @PostMapping("/education/save")
    public String saveEducation(EducationSaveDTO dto) {


        // 도서 정보 저장 로직
        educationService.saveEducation(dto);
        return "redirect:/admin/page";
    }
    @PostMapping("/education/delete")
    public String deleteEducation(Integer id) {


        // 도서 정보 삭제 로직
        educationService.deleteEducation(id);
        return "redirect:/admin/page";
    }
    @PostMapping("/education/day/update/return")
    public String updateDayReturn(@ModelAttribute EducationDaySaveDTO dto){
        dayOfWeekService.updateDayReturn(dto);
        return "redirect:/admin/page";
    }
    @PostMapping("/education/day/update/rental")
    public String updateDayRental(@ModelAttribute EducationDaySaveDTO dto){
        dayOfWeekService.updateDayRental(dto);

        return "redirect:/admin/page";
    }

    @PostMapping("/ok")
    public String rentalOk(Integer id, Model model) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        rentalService.okRental(id);

        return "redirect:/admin/page";
    }

    @GetMapping("/page")
    public String adminPage(Model model){
        List<Rental> list = rentalService.rentalListWaiting();
        model.addAttribute("list", list);
        DayOfWeek dayOfWeekReturn = dayOfWeekService.findByDayOfWeekReturn();
        DayOfWeek dayOfWeekRental = dayOfWeekService.findByDayOfWeekRental();

        model.addAttribute("dayOfWeekReturn", dayOfWeekReturn);
        model.addAttribute("dayOfWeekRental", dayOfWeekRental);

        return "admin/admin";
    }

    @PostMapping("/return")
    public String rentalReturn(Integer educationId){
        educationService.returnEducation(educationId);
        return "redirect:/user/list";
    }

    @GetMapping("/notification")
    public String notification(Model model){
        NotificationRequestDTO requestDTO = new NotificationRequestDTO();
        model.addAttribute("notificationDTO", requestDTO);

        List<Notification> notificationList = notificationService.notificationFindAll();
        model.addAttribute("notificationList", notificationList);

        return "admin/notification";
    }

    @PostMapping("/notification/save")
    public String notification(NotificationRequestDTO dto){
        notificationService.notificationSave(dto);
        return "redirect:/admin/notification";
    }

    @PostMapping("/notification/tomain/post")
    public String notificationToMainPost(Long id){
        notificationService.notificationToMainPost(id);
        return "redirect:/admin/notification";
    }
    @PostMapping("/notification/tomain/delete")
    public String notificationToMainDelete(Long id){
        notificationService.notificationToMainDelete(id);
        return "redirect:/admin/notification";
    }

}