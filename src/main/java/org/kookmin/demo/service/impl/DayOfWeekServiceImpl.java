package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.domain.DayOfWeek;
import org.kookmin.demo.dto.request.education.EducationDaySaveDTO;
import org.kookmin.demo.repository.DayOfWeekRepository;
import org.kookmin.demo.service.DayOfWeekService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DayOfWeekServiceImpl implements DayOfWeekService {
    private final DayOfWeekRepository dayOfWeekRepository;
    public DayOfWeek findByDayOfWeekReturn() {
        return dayOfWeekRepository.findById(1).orElseThrow();
    }
    public DayOfWeek findByDayOfWeekRental() {
        return dayOfWeekRepository.findById(2).orElseThrow();
    }

    @Transactional
    public void updateDayReturn(EducationDaySaveDTO dto) {
        DayOfWeek day = findByDayOfWeekReturn();
        day.updateReturnDTO(dto);
    }
    @Transactional
    public void updateDayRental(EducationDaySaveDTO dto) {
        DayOfWeek day = findByDayOfWeekRental();
        day.updateRentalDTO(dto);
    }
}
