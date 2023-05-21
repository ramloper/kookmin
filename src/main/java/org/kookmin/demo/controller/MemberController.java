package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.dto.request.member.MemberSaveRequestDTO;
import org.kookmin.demo.exception.UserNameExistException;
import org.kookmin.demo.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void login(String error, String logout){
        log.info("login get.......");
        log.info("logout : " + logout);

        if (logout != null) log.info("user logout===========================");
    }

    @GetMapping("/joinPage")
    public String joinPage(){
        return "user/join";
    }

    @PostMapping("/join")
    public String join(MemberSaveRequestDTO dto, RedirectAttributes redirect){

        try {
            memberService.saveMember(dto);
        }catch (UserNameExistException e) {
            redirect.addFlashAttribute("error", "username");
            return "redirect:/user/joinPage";
        }
        redirect.addFlashAttribute("result", "success");
        return "redirect:/user/login";
    }
}
