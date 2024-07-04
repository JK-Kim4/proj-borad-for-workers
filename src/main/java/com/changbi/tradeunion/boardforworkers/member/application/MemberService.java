package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberDetailDto;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberListDto;

import java.util.List;

public interface MemberService {

    Long save(MemberSaveDto memberSaveDto);

    Long savePreMember(MemberSaveDto memberSaveDto);

    Long saveMemberByPreMember(PreMember preMember);

    void update(MemberSaveDto memberSaveDto);

    void delete(Member member);

    void deletePreMember(PreMember preMember);

    MemberDetailDto findById(Long memberId);

    PreMember findPreMemberById(Long memberId);

    List<MemberListDto> findAll(Pagination pagination);

    Member findByMemberEmail(String memberName);
}
