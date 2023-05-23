package org.kookmin.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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



    @OneToOne
    private EducationImage image;

    @OneToMany(mappedBy = "education")
    private List<Rental> rental = new ArrayList<>();
}
