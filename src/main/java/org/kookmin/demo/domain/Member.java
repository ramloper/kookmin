package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.common.MemberStatus;

import javax.persistence.*;
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

    private String MemberName;

    private String phoneNumber;

    private MemberStatus status;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Rental> Rental = new ArrayList<>();

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
}
