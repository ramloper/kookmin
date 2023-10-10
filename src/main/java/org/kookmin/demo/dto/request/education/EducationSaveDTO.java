package org.kookmin.demo.dto.request.education;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.domain.Education;
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

    private String storeFileUrl;

    private String originFileName;

    public Education toEntity() {
        return Education.builder()
                .writer(writer)
                .translator(translator)
                .bookName(bookName)
                .publisher(publisher)
                .storeFileUrl(storeFileUrl)
                .originFileName(originFileName)
                .status(EducationStatus.대여가능)
                .build();
    }
}
