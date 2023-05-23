package org.kookmin.demo.service;

import org.kookmin.demo.dto.request.member.MemberModifyDTO;
import org.kookmin.demo.dto.request.member.MemberSaveDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;
import org.kookmin.demo.exception.UserNameExistException;

import java.util.List;

public interface MemberService {

    void saveMember(MemberSaveDTO dto) throws UserNameExistException;

    List<MemberResponseDTO> findAllMember();

    MemberResponseDTO findMemberById(String studentId);

    void updateMember(MemberModifyDTO dto);

    void deleteMember(String studentId);
}
