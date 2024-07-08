package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface MemberService {

    Long save(MemberSaveDto memberSaveDto);

    Long savePreMember(PreMemberDto preMemberDto);

    Long saveMemberByPreMember(PreMember preMember);

    void update(MemberSaveDto memberSaveDto);

    void delete(Long memberId);

    void deletePreMember(PreMember preMember);

    List<MemberListDto> findMembers(Pagination pagination);

    MemberDetailDto findById(Long memberId);

    List<PreMemberDto> findPreMembers();

    PreMember findPreMemberById(Long memberId);

    Member findByMemberEmail(String memberName);

    void preMemberSignUpProcess(String processType, Long preMemberId);

    void memberLogin(LoginInformation loginInformation, HttpSession session);

    void memberLogout(SessionMember sessionMember, HttpSession session);
}
