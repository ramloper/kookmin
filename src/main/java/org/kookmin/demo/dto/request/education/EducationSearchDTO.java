package org.kookmin.demo.dto.request.education;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EducationSearchDTO {
    private String name;

    private String searchType;
}
