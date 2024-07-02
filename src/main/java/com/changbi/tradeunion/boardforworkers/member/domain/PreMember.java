package com.changbi.tradeunion.boardforworkers.member.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Company;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
import jakarta.persistence.*;

import java.time.LocalDateTime;


/**
 * PreMember Entity
 *
 * 노조 게시판 회원 가입 시 관리자의 가입 승인 절차가 필요합니다.
 * 회원 가입 요청 사용자는 우선 Pre Member Entity 형태로 저장됩니다.
 *
 * (회원 가입 승인 시) Per Member -> Member
 * (회원 가입 거절 시) 삭제
 * */
@Entity
public class PreMember {

    @Id
    @GeneratedValue
    @Column(name = "pre_member_id")
    private Long id;

    @Column(length = 32)
    private String memberEmail;

    @Column(length = 32)
    private String memberRealName;

    @Column(length = 64)
    private String memberNickName;

    @Column
    private String memberPassword;

    @Column
    @Enumerated(EnumType.STRING)
    private Company company;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    private LocalDateTime requestDateTime;
}
