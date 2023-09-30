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

import java.util.ArrayList;
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
        if (dto.getMemberName() != null && !dto.getMemberName().equals("")) {
            memberRepository.modifyingMemberName(username, dto.getMemberName());
        }
        if (dto.getPassword() != null && !dto.getPassword().equals("")) {
            memberRepository.modifyingPassword(username, encoder.encode(dto.getPassword()));
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().equals("")) {
            memberRepository.modifyingPhoneNumber(username, dto.getPhoneNumber());
        }
    }

    @Override
    @Transactional
    public void deleteMember(String username) {
        Member member = memberRepository.findById(username).orElseThrow();
        memberRepository.delete(member);
    }

    @Transactional
    public void modifyPassword(MemberModifyDTO dto){
        memberRepository.modifyingPassword(dto.getUsername(), encoder.encode(dto.getPassword()));
    }

    public boolean findByUserName(MemberSaveDTO memberSaveDTO){
        boolean exist = memberRepository.existsById(memberSaveDTO.getUsername());
        if (exist) return true;
        else return false;
    }
}
