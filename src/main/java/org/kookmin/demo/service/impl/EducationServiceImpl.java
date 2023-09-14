package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final RentalRepository rentalRepository;

    @Value("${file.path}")
    String savePath;

    @Override
    public void saveEducation(EducationSaveDTO dto) {
        // 도서 정보 및 업로드된 파일 처리
        MultipartFile file = dto.getFile();
        if (file != null && !file.isEmpty()) {

            try {
                // 파일 저장 경로
                // 파일 이름
                UUID uuid = UUID.randomUUID();
                String[] uuids = uuid.toString().split("-");
                String uniqueName = uuids[0];
                // 파일 저장
                String fileName = dto.getFile().getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf("."));
                File img_File = new File(savePath +uniqueName+fileExt);

                file.transferTo(img_File);
                // 파일 이름을 DTO에 설정
                dto.setUploadFileName(uniqueName+fileExt);
                dto.setOriginFileName(file.getOriginalFilename());


            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                e.printStackTrace();

            }
        }

        educationRepository.save(dto.toEntity());

    }

    @Override
    @Transactional
    public void returnEducation(Integer id){
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setStatus(RentalStatus.DELETED);
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
    public void deleteEducation(Integer id) {
        Education education = educationRepository.findById(id).orElseThrow();
        educationRepository.delete(education);
    }
}
