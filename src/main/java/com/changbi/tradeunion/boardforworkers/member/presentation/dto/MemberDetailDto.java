package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberDetailDto {

    private Long memberId;
    private String memberEmail;
    private String memberRealName;
    private String memberNickName;
    private String department;
    private String company;
    private String role;
    private LocalDateTime appendDate;
    private Long appendAdminId;
    private LocalDateTime updateDate;


}
