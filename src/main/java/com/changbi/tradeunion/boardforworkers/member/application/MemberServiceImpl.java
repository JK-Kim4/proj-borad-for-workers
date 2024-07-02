package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberNotFountException;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberRepository memberRepository;

    @Override
    public Long save(MemberSaveDto memberSaveDto) {
        Member member = memberSaveDto.toEntity();

        if(isAlreadyExistMemberEmail(member.getMemberEmail())){
            throw new MemberDuplicateException();
        }

        return memberRepository.save(member);
    }

    @Override
    public Long savePreMember(MemberSaveDto memberSaveDto) {
        PreMember preMember = memberSaveDto.toPreEntity();

        if(isAlreadyExistMemberEmail(preMember.getMemberEmail()) ||
                isAlreadyExistPreMemberEmail(preMember.getMemberEmail())){
            throw new MemberDuplicateException();
        }

        return memberRepository.savePreMember(preMember);
    }

    @Override
    public Long saveMemberByPreMember(PreMember preMember) {
        Member member = preMember.toMember();

        if(isAlreadyExistMemberEmail(member.getMemberEmail())){
            throw new MemberDuplicateException();
        }

        //insert Member
        memberRepository.save(member);

        //delete PreMember
        memberRepository.deletePreMember(preMember);

        return member.getId();
    }

    @Override
    public void update(MemberSaveDto memberSaveDto){
        Member member = memberRepository.findById(memberSaveDto.getMemberId());

        if(member == null) {
            throw new MemberNotFountException();
        }

        member.update(memberSaveDto);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public void deletePreMember(PreMember preMember) {
        memberRepository.deletePreMember(preMember);
    }

    @Transactional(readOnly = true)
    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public PreMember findPreMemberById(Long preMemberId) {
        return memberRepository.findPreMemberById(preMemberId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Member> findAll(Pagination pagination) {
        return memberRepository.findAll(pagination);
    }

    @Transactional(readOnly = true)
    @Override
    public Member findByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName);
    }

    private boolean isAlreadyExistMemberEmail(String memberEmail) {
        return memberRepository.isAlreadyExistMemberEmail(memberEmail);
    }

    private boolean isAlreadyExistPreMemberEmail(String memberEmail) {
        return memberRepository.isAlreadyExistPreMemberEmail(memberEmail);
    }
}
