package org.kookmin.demo.service;

import org.kookmin.demo.domain.Member;
import org.kookmin.demo.dto.request.member.MemberModifyDTO;
import org.kookmin.demo.dto.request.member.MemberSaveDTO;
import org.kookmin.demo.dto.response.MemberResponseDTO;
import org.kookmin.demo.exception.UserNameExistException;

import java.security.Principal;
import java.util.List;

public interface MemberService {

    boolean findByUserName(MemberSaveDTO memberSaveDTO);
    void saveMember(MemberSaveDTO dto) throws UserNameExistException;

    List<Member> findAllMember();

    Member findMemberById(String studentId);

    void modifyMember(String username, MemberModifyDTO dto);

    void deleteMember(String username);

    void modifyPassword(MemberModifyDTO dto);
}
