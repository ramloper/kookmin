package org.kookmin.demo.service;

import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.education.EducationModifyDTO;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.dto.request.education.EducationSearchDTO;
import org.kookmin.demo.dto.response.EducationResponseDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EducationService {

    void saveEducation(EducationSaveDTO dto);

    void returnEducation(Integer id);

    List<Education> findAllEducationByRental();

    List<Education> findAllEducation();

    Page<Education> findAllPage(Pageable pageable);

    Page<Education> findAllSearchType(EducationSearchDTO dto, Pageable pageable);


    void updateEducation(EducationModifyDTO dto);

    void deleteEducation(Integer id);



}
