package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.dto.request.education.EducationDaySaveDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean monDay;

    private boolean tuesDay;

    private boolean wednesDay;

    private boolean thursDay;

    private boolean friDay;

    public void updateRentalDTO(EducationDaySaveDTO dto) {
        this.setMonDay(dto.isMonDay());
        this.setTuesDay(dto.isTuesDay());
        this.setWednesDay(dto.isWednesDay());
        this.setThursDay(dto.isThursDay());
        this.setFriDay(dto.isFriDay());
    }
    public void updateReturnDTO(EducationDaySaveDTO dto) {
        this.setMonDay(dto.isMonDay());
        this.setTuesDay(dto.isTuesDay());
        this.setWednesDay(dto.isWednesDay());
        this.setThursDay(dto.isThursDay());
        this.setFriDay(dto.isFriDay());
    }
}
