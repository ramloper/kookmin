package org.kookmin.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kookmin.demo.domain.*;
import org.kookmin.demo.dto.request.education.EducationDaySaveDTO;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.dto.request.member.MemberModifyDTO;
import org.kookmin.demo.dto.request.notification.NotificationRequestDTO;
import org.kookmin.demo.service.*;
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
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final EducationService educationService;
    private final RentalService rentalService;
    private final DayOfWeekService dayOfWeekService;
    private final NotificationService notificationService;
    private final MemberService memberService;

    @PostMapping("/modify")
    public String modifyMember(Principal principal, MemberModifyDTO dto){
        memberService.modifyMember(principal.getName(), dto);
        return "redirect:/admin/page";
    }

    @PostMapping("/education/save")
    public String saveEducation(EducationSaveDTO dto) {


        // 도서 정보 저장 로직
        educationService.saveEducation(dto);
        return "redirect:/admin/page";
    }
    @PostMapping("/education/delete")
    public String deleteEducation(Integer educationId) {

        System.out.println("컨트롤러 들어옴?");
        // 도서 정보 삭제 로직 (소프트 delete)
        educationService.deleteEducation(educationId);
        return "redirect:/admin/list";
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

        DayOfWeek dayOfWeekReturn = dayOfWeekService.findByDayOfWeekReturn();
        DayOfWeek dayOfWeekRental = dayOfWeekService.findByDayOfWeekRental();

        model.addAttribute("dayOfWeekReturn", dayOfWeekReturn);
        model.addAttribute("dayOfWeekRental", dayOfWeekRental);

        return "admin/admin";
    }

    @PostMapping("/return")
    public String rentalReturn(Integer educationId){
        educationService.returnEducation(educationId);
        return "redirect:/admin/list";
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

    @GetMapping("/list")
    public String detailMember(Model model){
        List<Rental> list = rentalService.rentalListWaiting();
        model.addAttribute("list", list);
        List<Member> memberList = memberService.findAllMember();
        model.addAttribute("memberList", memberList);
        List<Rental> rentalList = rentalService.rentalListReserved();
        model.addAttribute("rentalList", rentalList);
        List<Education> educationList = educationService.findAllEducation();
        model.addAttribute("educationList", educationList);
        return "admin/list";
    }
}