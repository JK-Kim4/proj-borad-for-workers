package com.changbi.tradeunion.boardforworkers.common.dto;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
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
    @Size(min= 6, max = 16, message = "계정은 6글자 이상 16자 이하의 공백없는 문자열만 가능합니다.")
    private String memberName;

    @NotBlank(message = "패스워드를 입럭해주세요.")
    private String memberPassword;

    private String memberNickName;

    @NotBlank(message = "부서를 선택해주세요.")
    private String department;

    @NotBlank(message = "사용자 등급을 선택해주세요.")
    private String role;

    public Member toEntity(){
        return Member.builder()
                .memberName(this.memberName)
                .memberPassword(this.memberPassword)
                .department(this.department)
                .memberNickName(this.memberNickName)
                .role(this.role)
                .build();
    }

}
