package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SessionMember {

    private Long memberId;
    private String memberEmail;
    private String memberRealName;
    private String memberNickName;

    private String company;
    private String department;
    private String role;


    @Builder
    public SessionMember(Member member){
        this.memberId = member.getId();
        this.memberEmail = member.getMemberEmail();
        this.memberRealName = member.getMemberRealName();
        this.memberNickName = member.getMemberNickName();

        this.company = member.getCompany().name();
        this.department = member.getDepartment().name();
        this.role = member.getRole().name();
    }
}
