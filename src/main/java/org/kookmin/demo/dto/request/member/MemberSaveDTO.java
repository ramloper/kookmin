package org.kookmin.demo.dto.request.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberSaveDTO {

    @NotBlank(message = "학번은 필수입니다.")
    @Pattern(regexp = "^[A-Z][0-9]{7}$", message = "학번은 대문자로 시작하고 7자리 숫자여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^[0-9]{11}$", message = "휴대폰 번호는 10자리의 숫자만 허용됩니다.")
    private String phoneNumber;

}
