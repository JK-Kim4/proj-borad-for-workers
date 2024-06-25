package com.changbi.tradeunion.boardforworkers.common.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberSaveDto {

    private Long memberId;
    private String memberName;
    private String memberPassword;
    private String department;
    private String role;

    public Member toEntity(){
        return Member.builder()
                .memberName(this.memberName)
                .memberPassword(this.memberPassword)
                .department(this.department)
                .role(this.role)
                .build();
    }

}
