package org.kookmin.demo.dto.request.member;

import lombok.Data;

@Data
public class MemberSaveDTO {

    private String username;

    private String password;

    private String name;

    private String phoneNumber;

}
