package com.changbi.tradeunion.boardforworkers.common.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class MailSenderDto {

    private String title;       // 제목
    private String body;        // 내용
    private String from;        // 보내는 사람
    private String to;          // 받는 사람

    private String mailSenderUser;      // 창비 메일러 사용자 계정
    private String mailSenderPassword;  // 창비 메일러 사용자 패스워드

    private String type = "text/html; charset=utf-8";

    @Builder
    public MailSenderDto(
            String title, String body, String from, String to){

        this.title = title;
        this.body = body;
        this.from = from;
        this.to = to;

    }

    public void setMailSenderAccount(String mailSenderUser, String mailSenderPassword){
        this.mailSenderUser = mailSenderUser;
        this.mailSenderPassword = mailSenderPassword;
    }

}
