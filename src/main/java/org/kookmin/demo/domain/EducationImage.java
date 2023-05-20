package org.kookmin.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class EducationImage {

    @Id
    private String name;

    private String imagePath;

    @OneToOne(mappedBy = "image")
    private Education education;

    public String getImageUrl(){
        return "/image/" + name;
    }
}
