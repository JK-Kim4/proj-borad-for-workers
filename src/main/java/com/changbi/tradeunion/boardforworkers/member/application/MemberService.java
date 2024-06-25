package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;

public interface MemberService {

    Long save(MemberSaveDto memberSaveDto);

    void update(MemberSaveDto memberSaveDto);

    Member findById(Long memberId);
}
