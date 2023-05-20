package org.kookmin.demo.service;

import org.kookmin.demo.dto.request.member.MemberModifyRequestDTO;
import org.kookmin.demo.dto.request.member.MemberSaveRequestDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    void saveMember(MemberSaveRequestDTO request);

    List<MemberResponseDTO> findAllMember();

    MemberResponseDTO findMemberById(String studentId);

    void updateMember(MemberModifyRequestDTO request);

    void deleteMember(String studentId);
}
