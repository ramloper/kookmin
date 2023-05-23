package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.common.MemberRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{

    @Id
    private String username; // 학번

    @Column(columnDefinition = "TEXT")
    private String password;

    private String name;

    private String phoneNumber;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    @OneToMany(mappedBy = "member")
    private List<Rental> education = new ArrayList<>();

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void changPassword(String password) { this.password = password; }
}
