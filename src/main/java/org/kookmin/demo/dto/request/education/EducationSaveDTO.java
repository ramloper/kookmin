package org.kookmin.demo.dto.request.education;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.EducationImage;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Data
public class EducationSaveDTO {
    private String writer;

    private String translator;

    private String bookName;

    private String publisher;

    private MultipartFile file;

    public Education toEntity() {
        return Education.builder()
                .writer(writer)
                .translator(translator)
                .bookName(bookName)
                .publisher(publisher)
                .fileName(file.getOriginalFilename())
                .build();
    }
}
