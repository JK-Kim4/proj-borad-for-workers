package com.changbi.tradeunion.boardforworkers.member.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import com.changbi.tradeunion.boardforworkers.common.dto.MemberSaveDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 32)
    private String memberName;

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


    //constructor
    public Member (MemberSaveDto memberSaveDto){
        this.memberName = memberSaveDto.getMemberName();
        this.memberPassword = memberSaveDto.getMemberPassword();
        this.department = Department.valueOf(memberSaveDto.getDepartment());
        this.role = Role.valueOf(memberSaveDto.getRole());
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @Builder
    public Member (
            String memberName, String memberPassword,
            String department, String role){
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.department = Department.valueOf(department);
        this.role = Role.valueOf(role);

    }

    public void update(MemberSaveDto memberSaveDto) {

    }

    /*public method
    * 필드 맴버 연산
    * */

    /*private method
    * 필드 맴버 값 변경
    * */
}
