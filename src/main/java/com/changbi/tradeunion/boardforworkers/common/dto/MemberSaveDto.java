package com.changbi.tradeunion.boardforworkers.common.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import com.changbi.tradeunion.boardforworkers.member.domain.PreMember;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberSaveDto {

    private Long memberId;

    @NotBlank(message = "사용자 계정을 입력해주세요.")
    @Size(min= 6, max = 16, message = "[*@changbi.com]형식의 메일 주소를 입력해주세요.")
    private String memberEmail;

    @NotBlank(message = "사용자의 실명을 입력해주세요.")
    @Size(min = 1, max = 20, message = "본명은 한글로 1글자 이상 20자 이하의 공백없는 문자열만 가능합니다.")
    private String memberRealName;

    @NotBlank(message = "패스워드를 입럭해주세요.")
    private String memberPassword;

    private String memberNickName;

    @NotBlank(message = "소속 회사를 선택해주세요")
    private String company;

    @NotBlank(message = "부서를 선택해주세요.")
    private String department;

    @NotBlank(message = "사용자 등급을 선택해주세요.")
    private String role;

    public Member toEntity(){
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberRealName(this.memberRealName)
                .memberPassword(this.memberPassword)
                .department(this.department)
                .memberNickName(this.memberNickName)
                .role(this.role)
                .build();
    }

    public PreMember toPreEntity(){
        return PreMember.builder()
                .memberEmail(this.memberEmail)
                .company(this.company)
                .memberRealName(this.memberRealName)
                .memberPassword(this.memberPassword)
                .memberNickName(this.memberNickName)
                .department(this.department)
                .build();
    }

}
