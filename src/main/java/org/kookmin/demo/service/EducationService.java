package org.kookmin.demo.service;

import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.education.EducationModifyDTO;
import org.kookmin.demo.dto.response.EducationResponseDTO;

import java.util.List;

public interface EducationService {

    void saveEducation(EducationModifyDTO dto);

    List<Education> findAllEducation();

    List<Education> findAllEducationJoinMember();

    EducationResponseDTO findEducationById(String studentId);

    void updateEducation(EducationModifyDTO dto);

    void okEducation(Integer id);
    void deleteEducation(String studentId);



}
