package org.kookmin.demo.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.kookmin.demo.common.RentalStatus;

import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private RentalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Education education;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private DayOfWeek dayOfWeek;

    private String returnDate;

    private String rentalDate;
}
