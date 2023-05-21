package org.kookmin.demo.service;

import org.kookmin.demo.dto.request.member.MemberModifyRequestDTO;
import org.kookmin.demo.dto.request.member.MemberSaveRequestDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;
import org.kookmin.demo.exception.UserNameExistException;

import java.util.List;

public interface MemberService {

    void saveMember(MemberSaveRequestDTO dto) throws UserNameExistException;

    List<MemberResponseDTO> findAllMember();

    MemberResponseDTO findMemberById(String studentId);

    void updateMember(MemberModifyRequestDTO dto);

    void deleteMember(String studentId);
}
