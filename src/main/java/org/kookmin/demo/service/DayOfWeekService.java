package org.kookmin.demo.service;

import org.kookmin.demo.domain.DayOfWeek;
import org.kookmin.demo.dto.request.education.EducationDaySaveDTO;

public interface DayOfWeekService{
    DayOfWeek findByDayOfWeekReturn();
    DayOfWeek findByDayOfWeekRental();

    void updateDayReturn(EducationDaySaveDTO dto);
    void updateDayRental(EducationDaySaveDTO dto);
}
