package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.dto.request.member.MemberModifyDTO;
import org.kookmin.demo.dto.request.member.MemberSaveDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;
import org.kookmin.demo.exception.UserNameExistException;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;


    @Value("${file.path}")
    private String uploadFolder;
    @Override
    public void saveMember(MemberSaveDTO dto) throws UserNameExistException {
        boolean exist = memberRepository.existsById(dto.getUsername());

        if (exist) throw new UserNameExistException();
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        member.addRole(MemberRole.USER);
        memberRepository.save(member);
    }

    @Override
    public List<MemberResponseDTO> findAllMember() {
        return null;
    }

    @Override
    public MemberResponseDTO findMemberById(String studentId) {
        return null;
    }

    @Override
    public void updateMember(MemberModifyDTO request) {

    }

    @Override
    public void deleteMember(String studentId) {

    }
}
