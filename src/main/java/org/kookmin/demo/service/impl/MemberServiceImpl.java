package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.MemberRole;
import org.kookmin.demo.common.MemberStatus;
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
import org.springframework.transaction.annotation.Transactional;

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
                .MemberName(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        member.addRole(MemberRole.USER);
        memberRepository.save(member);
    }

    @Override
    public List<Member> findAllMember() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    @Override
    public Member findMemberById(String studentId) {
        Member member = memberRepository.findById(studentId).orElseThrow();
        return member;
    }

    @Override
    @Transactional
    public void modifyMember(String username, MemberModifyDTO dto) {
        Member member = memberRepository.findById(username).orElseThrow();

        System.out.println("=================");
        System.out.println(member.getMemberName());
        System.out.println(member.getPhoneNumber());
        if (dto.getMemberName() != null) {
            member.setMemberName(dto.getMemberName());
        }
        if (dto.getPassword() != null) {
            member.setPassword(encoder.encode(dto.getPassword()));
        }
        if (dto.getPhoneNumber() != null) {
            member.setPhoneNumber(dto.getPhoneNumber());
        }
        System.out.println("=================");
        System.out.println(member.getMemberName());
        System.out.println(member.getPhoneNumber());
    }

    @Override
    @Transactional
    public void deleteMember(String studentId) {
        Member member = memberRepository.findById(studentId).orElseThrow();

        member.setStatus(MemberStatus.INACTIVE);
    }
}
