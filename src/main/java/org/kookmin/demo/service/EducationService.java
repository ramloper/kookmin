package org.kookmin.demo.service;

import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.education.EducationModifyRequestDTO;
import org.kookmin.demo.dto.response.EducationResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EducationService {

    void saveEducation(EducationModifyRequestDTO dto);

    List<Education> findAllEducation();

    EducationResponseDTO findEducationById(String studentId);

    void updateEducation(EducationModifyRequestDTO dto);

    void deleteEducation(String studentId);

}
