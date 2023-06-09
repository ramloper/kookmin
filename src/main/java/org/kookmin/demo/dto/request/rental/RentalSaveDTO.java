package org.kookmin.demo.dto.request.rental;

import lombok.Data;
import lombok.Getter;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;

@Getter
@Data
public class RentalSaveDTO {

    private Integer educationId;
    private String memberId;

    public Rental toEntity(Member member, Education education){
        return Rental.builder()
                .education(education)
                .member(member)
                .status(RentalStatus.WAITING)
                .build();
    }
}
