package org.kookmin.demo.dto.request.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MemberLoginDTO {

    @NotBlank(message = "학번은 필수입니다.")
    @Pattern(regexp = "^[A-Z][0-9]{7}$", message = "학번은 대문자로 시작하고 7자리 숫자여야 합니다.")
    private String studentId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
