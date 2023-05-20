package org.kookmin.demo.domain;

import lombok.*;
import org.kookmin.demo.common.MemberRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
}
