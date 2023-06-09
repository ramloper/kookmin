package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PropertySource("classpath:/application-dev.properties")
public class AdminController {
    private final EducationService educationService;
    private final RentalService rentalService;

    @Value("${file.path}")
    String savePath;

    @PostMapping("/education/save")
    public String saveEducation(EducationSaveDTO dto) {
        // 도서 정보 및 업로드된 파일 처리
        MultipartFile file = dto.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                // 파일 저장 경로
                // 파일 이름
                String fileName = file.getOriginalFilename();
                // 파일 저장
                file.transferTo(new File(savePath + fileName));
                // 파일 이름을 DTO에 설정
                dto.setFile(file);
            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                e.printStackTrace();
            }
        }

        // 도서 정보 저장 로직
        educationService.saveEducation(dto);
        return "redirect:/admin/page";
    }
    @PostMapping("/ok")
    public String rentalOk(Integer id, Model model){
        rentalService.okRental(id);
        List<Rental> list = rentalService.rentalList();

        model.addAttribute("list", list);
        return "admin";
    }

    @GetMapping("/page")
    public String adminPage(Model model){
        List<Rental> list = rentalService.rentalList();
        model.addAttribute("list", list);

        return "admin";
    }


}

//json 통신? 그렇죠
