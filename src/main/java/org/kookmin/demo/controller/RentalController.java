package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.member.MemberSaveDTO;
import org.kookmin.demo.dto.request.rental.RentalSaveDTO;
import org.kookmin.demo.exception.UserNameExistException;
import org.kookmin.demo.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/save")
    public String insertRental(RentalSaveDTO dto, RedirectAttributes redirect) {

        try {
            rentalService.rentalSave(dto);
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "failSave");
            return "redirect:/main";
        }
        redirect.addFlashAttribute("result", "success");
        return "redirect:/main";
    }

    @PostMapping("/cancel")
    public String cancelRental(Integer id){
        rentalService.rentalCancel(id);

        return "redirect:/user/page";
    }
}
