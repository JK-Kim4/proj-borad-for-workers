package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberNotFountException;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.MemberListDto;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final HttpServletRequest request;
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
        Member admin = this.getSessionAdmin();
        Member member = preMember.toMember(admin.getId());

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

        if(Objects.isNull(member)) {
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
    public List<MemberListDto> findAll(Pagination pagination) {
        return memberRepository.findAll(pagination)
                .stream()
                .map(member -> MemberListDto.builder()
                                        .member(member)
                                    .build())
                .toList();

    }
    @Transactional(readOnly = true)
    @Override
    public Member findByMemberEmail(String findByMemberEmail) {
        return memberRepository.findByMemberEmail(findByMemberEmail);
    }

    private boolean isAlreadyExistMemberEmail(String memberEmail) {
        return memberRepository.isAlreadyExistMemberEmail(memberEmail);
    }

    private boolean isAlreadyExistPreMemberEmail(String memberEmail) {
        return memberRepository.isAlreadyExistPreMemberEmail(memberEmail);
    }

    private Member getSessionAdmin() {
        HttpSession session = request.getSession();
        return (Member) session.getAttribute("admin");
    }
}
