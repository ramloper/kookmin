package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.common.EducationStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bookName;

    private String publisher;

    private String writer;

    private String translator;

    private String fileName;

    private EducationStatus status;



    @OneToOne
    private EducationImage image;

    @OneToMany(mappedBy = "education")
    private List<Rental> rental = new ArrayList<>();
}
