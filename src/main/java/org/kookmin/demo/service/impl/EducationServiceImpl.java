package org.kookmin.demo.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.education.EducationModifyDTO;
import org.kookmin.demo.dto.request.education.EducationSaveDTO;
import org.kookmin.demo.dto.request.education.EducationSearchDTO;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.RentalRepository;
import org.kookmin.demo.service.EducationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final RentalRepository rentalRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void saveEducation(EducationSaveDTO dto) {
        // 도서 정보 및 업로드된 파일 처리
        MultipartFile file = dto.getFile();

        ObjectMetadata metadata = new ObjectMetadata();
        if (file != null && !file.isEmpty()) {

            try {
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());
                String originalFileName = file.getOriginalFilename();
                int index = originalFileName.lastIndexOf(".");
                String ext = originalFileName.substring(index + 1);

                String storeFileName = UUID.randomUUID() + "." + ext;
                String key = "BookImages/" + storeFileName;
                try (InputStream inputStream = file.getInputStream()){
                    amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                }
                String storeFileUrl = amazonS3Client.getUrl(bucket, key).toString();
                dto.setStoreFileUrl(storeFileUrl);
                dto.setOriginFileName(originalFileName);


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
        rental.setStatus(RentalStatus.삭제);
        Education education = educationRepository.findById(rental.getEducation().getId()).orElseThrow();
        education.setStatus(EducationStatus.AVAILABLE);
    }
    @Override
    public List<Education> findAllEducation() {
        List<Education> list = educationRepository.findAllEducation(EducationStatus.DELETED);
        return list;
    }
    @Override
    public List<Education> findAllEducationByRental() {
        List<Education> list = educationRepository.findAllByRental(EducationStatus.DELETED);
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


    @Override
    public void updateEducation(EducationModifyDTO dto) {

    }

    @Override
    @Transactional
    public void deleteEducation(Integer id) {
        System.out.println("서비스단 들어옴?");
        Education education = educationRepository.findById(id).orElseThrow();
        System.out.println("education before : " + education.getStatus());
        education.setStatus(EducationStatus.DELETED);
        System.out.println("education after : " + education.getStatus());
    }
}
