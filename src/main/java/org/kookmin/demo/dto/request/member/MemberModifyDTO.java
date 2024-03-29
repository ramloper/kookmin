package org.kookmin.demo.dto.request.member;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MemberModifyDTO {

    private String username;

    private String memberName;

    private String password;

    private String passwordCheck;

    private String phoneNumber;
}
