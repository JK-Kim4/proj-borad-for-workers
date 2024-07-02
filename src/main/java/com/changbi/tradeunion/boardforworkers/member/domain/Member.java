package com.changbi.tradeunion.boardforworkers.member.domain;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Company;
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
    private String memberEmail;

    @Column(length = 32)
    private String memberRealName;

    @Column(length = 64)
    private String memberNickName;

    @Column
    private String memberPassword;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    @Enumerated(EnumType.STRING)
    private Company company;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private LocalDateTime appendDate;

    @Column
    private Long appendAdminId;

    @Column
    private LocalDateTime updateDate;


    //constructor
    public Member (MemberSaveDto memberSaveDto){
        this.memberEmail = memberSaveDto.getMemberEmail();
        this.memberRealName = memberSaveDto.getMemberRealName();
        this.memberPassword = memberSaveDto.getMemberPassword();
        this.company = Company.valueOf(memberSaveDto.getCompany());
        this.department = Department.valueOf(memberSaveDto.getDepartment());
        this.role = Role.valueOf(memberSaveDto.getRole());
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @Builder
    public Member (
            String memberEmail, String memberPassword,
            String memberNickName,String memberRealName,
            String company, String department,
            String role, Long appendAdminId){
        this.memberEmail = memberEmail;
        this.memberRealName = memberRealName;
        this.memberPassword = memberPassword;
        this.company = Company.valueOf(company);
        this.department = Department.valueOf(department);
        this.role = Role.valueOf(role);
        if(memberNickName != null){
            this.memberNickName = memberNickName;
        }
        this.appendDate = LocalDateTime.now();
        this.appendAdminId = appendAdminId;
    }

    /*public method
    * 필드 맴버 연산
    * */

    //회원 정보 수정
    public void update(MemberSaveDto memberSaveDto) {
        this.memberEmail = memberSaveDto.getMemberEmail();
        this.department = Department.valueOf(memberSaveDto.getDepartment());
        this.memberNickName = memberSaveDto.getMemberNickName();
        this.role = Role.valueOf(memberSaveDto.getRole());
        this.updateDate = LocalDateTime.now();
    }

    //회원 패스워드 수정
    public void updatePassword(String password){
        //TODO 비밀번호 암호화 적용
        this.memberPassword = password;
    }

    /*private method
    * 필드 맴버 값 변경
    * */
}
