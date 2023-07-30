package org.kookmin.demo.dto.request.education;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EducationDaySaveDTO {
    private boolean monDay;
    private boolean tuesDay;
    private boolean wednesDay;
    private boolean thursDay;
    private boolean friDay;

}
