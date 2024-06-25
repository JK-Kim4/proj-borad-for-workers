package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberRepository memberRepository;

    @Override
    public Long save(MemberSaveDto memberSaveDto) {
        Member member = memberSaveDto.toEntity();

        if(isAlreadyExistMemberName(member)){
            throw new MemberDuplicateException();
        }

        return memberRepository.save(member);
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private boolean isAlreadyExistMemberName(Member member) {
        return memberRepository.isAlreadyExistMemberName(member);
    }
}
