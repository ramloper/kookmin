package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.common.EducationStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String publisher;

    private String writer;

    private String translator;

    private String fileName;

    private EducationStatus status;

    @OneToOne
    private EducationImage image;
}
