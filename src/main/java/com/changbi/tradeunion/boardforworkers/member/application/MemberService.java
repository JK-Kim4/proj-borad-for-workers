package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    public Long save(MemberSaveDto memberSaveDto);
}
