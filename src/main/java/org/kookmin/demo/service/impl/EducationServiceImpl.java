package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.dto.request.education.EducationModifyRequestDTO;
import org.kookmin.demo.dto.response.EducationResponseDTO;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.service.EducationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    @Override
    public void saveEducation(EducationModifyRequestDTO dto) {

    }

    @Override
    public List<Education> findAllEducation() {
        List<Education> list = educationRepository.findAll();
        return list;
    }

    @Override
    public EducationResponseDTO findEducationById(String studentId) {
        return null;
    }

    @Override
    public void updateEducation(EducationModifyRequestDTO dto) {

    }

    @Override
    public void deleteEducation(String studentId) {

    }
}
