package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.dto.request.member.MemberModifyRequestDTO;
import org.kookmin.demo.dto.request.member.MemberSaveRequestDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Value("${file.path}")
    private String uploadFolder;
    @Override
    public void saveMember(MemberSaveRequestDTO request) {

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
    public void updateMember(MemberModifyRequestDTO request) {

    }

    @Override
    public void deleteMember(String studentId) {

    }
}
