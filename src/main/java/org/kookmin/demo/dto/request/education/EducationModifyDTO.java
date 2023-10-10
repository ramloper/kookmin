package org.kookmin.demo.dto.request.education;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class EducationModifyDTO {

    private Integer id;

    private String bookName;

    private String publisher;

    private String writer;

    private String translator;

}
