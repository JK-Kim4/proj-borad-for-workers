package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.MemberDetail;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberNotFountException;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberValidationException;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.*;
import com.changbi.tradeunion.boardforworkers.member.repository.MemberRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final HttpServletRequest request;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {

        try {
            Member member = memberRepository.findByMemberEmail(memberEmail);
            SessionMember sessionMember = SessionMember.builder().member(member).build();

            HttpSession session = request.getSession();
            session.setAttribute("member", sessionMember);
            if (Role.ADMIN.equals(member.getRole()) || Role.SUPER_ADMIN.equals(member.getRole())){
                session.setAttribute("isAdmin", true);
            }

            return new MemberDetail(member);
        }catch (NoResultException e){
            logger.error("[MEMBER LOGIN FAIL]", e);
            throw new  UsernameNotFoundException("회원이 존재하지 않습니다.");
        }
    }

    @Override
    public Long save(MemberSaveDto memberSaveDto) {
        Member member = memberSaveDto.toEntity();

        if(isAlreadyExistMemberEmail(member.getMemberEmail())){
            throw new MemberDuplicateException();
        }

        return memberRepository.save(member);
    }

    @Override
    public Long savePreMember(PreMemberDto preMemberDto) {
        PreMember preMember = preMemberDto.toPreEntity();

        if(isAlreadyExistMemberEmail(preMember.getMemberEmail()) ||
                isAlreadyExistPreMemberEmail(preMember.getMemberEmail())){
            throw new MemberDuplicateException();
        }

        return memberRepository.savePreMember(preMember);
    }

    @Override
    public Long saveMemberByPreMember(PreMember preMember) {
        //Member admin = this.getSessionAdmin();
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
    public void preMemberSignUpProcess(String processType, Long preMemberId) {

        PreMember preMember = this.findPreMemberById(preMemberId);

        if (CommonValues.PROCESS_TYPE_ACCEPT.equalsIgnoreCase(processType)){
            this.saveMemberByPreMember(preMember);
        }

        if (CommonValues.PROCESS_TYPE_DECLINE.equalsIgnoreCase(processType)){
            this.deletePreMember(preMember);
        }

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
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId);
        memberRepository.delete(member);
    }

    @Override
    public void deletePreMember(PreMember preMember) {
        memberRepository.deletePreMember(preMember);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MemberListDto> findMembers(Pagination pagination) {

        return memberRepository.findMembers(pagination)
                .stream()
                .map(member -> MemberListDto.builder()
                        .member(member)
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDetailDto findById(Long memberId) {
        return MemberDetailDto.builder().member(memberRepository.findById(memberId)).build();
    }

    @Override
    public List<PreMemberDto> findPreMembers() {
        return memberRepository.findPreMembers()
                .stream()
                .map(preMember -> PreMemberDto.builder()
                        .preMember(preMember)
                        .build())
                .toList();
    }

    @Override
    public PreMember findPreMemberById(Long preMemberId) {
        return memberRepository.findPreMemberById(preMemberId);
    }

    @Transactional(readOnly = true)
    @Override
    public Member findByMemberEmail(String findByMemberEmail) {
        return memberRepository.findByMemberEmail(findByMemberEmail);
    }

    @Override
    public void sendAuthNumber(HashMap authNumber) {
        System.out.println(authNumber.toString());
    }

    @Override
    public void memberLogin(LoginInformation dto, HttpSession session) {

        try {
            Member member = this.findByMemberEmail(dto.getMemberEmail());

            if (this.isEqualPassword(
                    member.getMemberPassword(),
                    dto.getMemberPassword())){
                logger.info("[Login Success] MemberId = {}\t, loginTime = {}", member.getId(), LocalDateTime.now());

                SessionMember sessionMember = SessionMember.builder().member(member).build();

                session.setAttribute("member", sessionMember);

            } else {
                logger.error("[LoginFailure] RequestEmail = {}\t, loginTime = {}]",dto.getMemberEmail(), LocalDateTime.now());
                throw new MemberValidationException(CommonValues.RESULT_MESSAGE_FAIL_MEMBER_LOGIN_VALIDATION);
            }

        }catch (NoResultException | NonUniqueResultException e) {
            logger.error("[LoginFailure] RequestEmail = {}\t, loginTime = {}]",dto.getMemberEmail(), LocalDateTime.now());
            throw new MemberValidationException(CommonValues.RESULT_MESSAGE_FAIL_MEMBER_LOGIN_VALIDATION);
        }

    }

    @Override
    public void memberLogout(HttpSession session) {
        session.removeAttribute("member");
        session.invalidate();
    }

    @Override
    public Pagination getMemberPagingInfo(Integer pageNum, Integer pageSize) {
        return Pagination.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .totalCount(memberRepository.getMemberTotalCount())
                .build();
    }

    /*PRIVATE METHODS*/
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


    private boolean isEqualPassword(String memberPassword, String loginPassword) {
        return memberPassword.equals(loginPassword);
    }
}
