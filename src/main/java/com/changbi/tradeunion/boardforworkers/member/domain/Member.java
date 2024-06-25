package com.changbi.tradeunion.boardforworkers.member.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_seq")
    private Long seq;

    @Column(length = 32)
    private String memberId;

    @Column
    private String memberPassword;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;
}
