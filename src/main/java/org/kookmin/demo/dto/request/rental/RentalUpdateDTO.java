package org.kookmin.demo.dto.request.rental;

import lombok.Data;
import lombok.Getter;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Data
public class RentalUpdateDTO {

    private Integer id;
    private String returnDate;
    private String rentalDate;

}
