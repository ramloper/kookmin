package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.education.EducationModifyDTO;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.dto.request.education.EducationSearchDTO;
import org.kookmin.demo.dto.response.EducationResponseDTO;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.RentalRepository;
import org.kookmin.demo.service.EducationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final RentalRepository rentalRepository;

    @Override
    public void saveEducation(EducationSaveDTO dto) {
        educationRepository.save(dto.toEntity());


        dto.getFile();
    }

    @Override
    @Transactional
    public void returnEducation(Integer id){
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setStatus(RentalStatus.WAITING);
        Education education = educationRepository.findById(rental.getEducation().getId()).orElseThrow();
        education.setStatus(EducationStatus.AVAILABLE);



    }

    @Override
    public List<Education> findAllEducation() {
        List<Education> list = educationRepository.findAllByRental();
        return list;
    }

    @Override
    public Page<Education> findAllPage(Pageable pageable){
        Page<Education> list = educationRepository.findAllByEducationPage(pageable, EducationStatus.AVAILABLE);

        return list;
    }

    @Override
    public Page<Education> findAllSearchType(EducationSearchDTO dto, Pageable pageable){
        Page<Education> list = null;
        if (dto.getSearchType().equals("title")) {
            list = educationRepository.findAllByBookName(pageable, dto.getName());
        }else if (dto.getSearchType().equals("writer")) {
            list = educationRepository.findAllByWriter(pageable, dto.getName());
        }else if (dto.getSearchType().equals("translator")) {
            list = educationRepository.findAllByTranslator(pageable, dto.getName());
        }else if (dto.getSearchType().equals("publisher")) {
            list = educationRepository.findAllByPublisher(pageable, dto.getName());
        }
        return list;
    }

    public List<Education> findAllEducationJoinMember(){
        List<Education> list = educationRepository.findAllByRental();
        return list;
    }

    @Override
    public EducationResponseDTO findEducationById(String studentId) {
        return null;
    }

    @Override
    public void updateEducation(EducationModifyDTO dto) {

    }


    @Transactional
    @Override
    public void okEducation(Integer id){
        Education education = educationRepository.findById(id).orElseThrow();
    }
    @Override
    public void deleteEducation(String studentId) {

    }
}
