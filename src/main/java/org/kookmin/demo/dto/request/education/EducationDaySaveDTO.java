package org.kookmin.demo.dto.request.education;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.kookmin.demo.domain.DayOfWeek;
import org.kookmin.demo.domain.Education;

@Data
@Getter
@Setter
public class EducationDaySaveDTO {
    private boolean monDay;
    private boolean tuesDay;
    private boolean wednesDay;
    private boolean thursDay;
    private boolean friDay;

    public DayOfWeek toEntity() {
        return DayOfWeek.builder()
                .monDay(monDay)
                .tuesDay(tuesDay)
                .wednesDay(wednesDay)
                .thursDay(thursDay)
                .friDay(friDay)
                .build();
    }
}
