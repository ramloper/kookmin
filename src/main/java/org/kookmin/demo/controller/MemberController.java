package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.member.MemberModifyDTO;
import org.kookmin.demo.dto.request.member.MemberSaveDTO;
import org.kookmin.demo.exception.UserNameExistException;
import org.kookmin.demo.service.MemberService;
import org.kookmin.demo.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RentalService rentalService;

    @GetMapping("/login")
    public void login(@RequestParam(required = false) String err, Model model) {
        if (err != null){
            model.addAttribute("err", "err");
            model.addAttribute("alertMessage", "ID, PassWord Check");
        }
    }


    @GetMapping("/joinPage")
    public String joinPage(@Valid MemberSaveDTO memberSaveDTO, BindingResult result, Model model) {
        model.addAttribute("result", result);
        model.addAttribute("memberSaveDTO", new MemberSaveDTO());
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberSaveDTO memberSaveDTO, BindingResult result, Model model) throws UserNameExistException {

        if (result.hasErrors()){
            return "user/join";
        }
        String error = "error";
        if (memberService.findByUserName(memberSaveDTO)){
            model.addAttribute("error", error);
            return "user/join";
        }
        memberService.saveMember(memberSaveDTO);

        return "redirect:/user/login";
    }

    @GetMapping("/member/info")
    @ResponseBody
    public String getMemberInfo(@RequestParam("studentId") String studentId) {
        // 회원 정보 조회 로직을 수행하여 결과를 반환합니다.
        // 예시로 회원 이름과 이메일을 가져온다고 가정합니다.
        Member member = memberService.findMemberById(studentId);
        if (member != null) {
            String memberInfo = "이름: " + member.getMemberName() + "<br>"
                    + "핸드폰 번호: " + member.getPhoneNumber();
            return memberInfo;
        } else {
            return "회원 정보를 찾을 수 없습니다.";
        }
    }
    @GetMapping("/page")
    public String userPage(Principal principal, Model model){
        List<Rental> list = rentalService.myRentalList(principal.getName());
        model.addAttribute("list", list);

        return "user/user";
    }

    @PostMapping("/modify")
    public String modifyMember(Principal principal, MemberModifyDTO dto){
        memberService.modifyMember(principal.getName(), dto);
        return "redirect:/user/page";
    }

    @PostMapping("/delete")
    public String deleteMember(String username){
        memberService.deleteMember(username);
        return "redirect:/admin/page";
    }

    @GetMapping("/password")
    public String password(Model model){
        model.addAttribute("memberModifyDTO", new MemberModifyDTO());
        return "user/password";
    }

    @PostMapping("/changePassword")
    public String changePassword(MemberModifyDTO memberModifyDTO){

        memberService.modifyPassword(memberModifyDTO);
        return "redirect:/user/login";
    }
}
