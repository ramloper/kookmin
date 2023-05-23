package org.kookmin.demo.controller;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.service.EducationService;
import org.kookmin.demo.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;
    private final RentalService rentalService;



}

//json 통신? 그렇죠
