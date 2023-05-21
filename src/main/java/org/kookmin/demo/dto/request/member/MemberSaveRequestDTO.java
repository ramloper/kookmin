package org.kookmin.demo.dto.request.member;

import lombok.Data;
import org.kookmin.demo.domain.Member;

@Data
public class MemberSaveRequestDTO {

    private String username;

    private String password;

    private String name;

    private String phoneNumber;

}
