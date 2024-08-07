package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberListDto {

    private Long memberId;
    private String memberEmail;
    private String memberRealName;
    private String memberNickName;
    private String department;
    private String company;
    private String role;
    private LocalDateTime appendDate;
    private LocalDateTime updateDate;

    @Builder
    public MemberListDto(Member member){
        this.memberId = member.getId();
        this.memberEmail = member.getMemberEmail();
        this.memberRealName = member.getMemberRealName();
        this.memberNickName = member.getMemberNickName();
        this.department = member.getDepartment().name();
        this.company = member.getCompany().name();
        this.role = member.getRole().name();
        this.appendDate = member.getAppendDate();
        this.updateDate = member.getUpdateDate();
    }
}
