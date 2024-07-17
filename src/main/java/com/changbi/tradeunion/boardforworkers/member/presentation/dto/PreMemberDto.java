package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PreMemberDto {

    private Long preMemberId;
    private String memberEmail;
    private String memberRealName;
    private String memberNickName;
    private String memberPassword;
    private String company;
    private String department;
    private LocalDateTime requestDateTime;

    @Builder
    public PreMemberDto(PreMember preMember) {
        this.preMemberId = preMember.getId();
        this.memberEmail = preMember.getMemberEmail();
        this.memberRealName = preMember.getMemberRealName();
        this.memberNickName = preMember.getMemberNickName();
        this.memberPassword = preMember.getMemberPassword();
        this.company = preMember.getCompany().name();
        this.department = preMember.getDepartment().name();
        this.requestDateTime = preMember.getRequestDateTime();
    }


    public PreMember toPreEntity(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return PreMember.builder()
                .memberEmail(this.memberEmail)
                .company(this.company)
                .memberRealName(this.memberRealName)
                .memberPassword(passwordEncoder.encode(this.memberPassword))
                .memberNickName(this.memberNickName)
                .department(this.department)
                .build();
    }
}
