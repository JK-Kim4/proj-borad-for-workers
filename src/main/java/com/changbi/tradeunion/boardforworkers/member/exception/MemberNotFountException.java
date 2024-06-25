package com.changbi.tradeunion.boardforworkers.member.exception;

public class MemberNotFountException extends RuntimeException{

    private static final String MEMBER_NOTFOUND_ERROR_MESSAGE = "회원이 존재하지 않습니다.";

    public MemberNotFountException() {
        super(MEMBER_NOTFOUND_ERROR_MESSAGE);
    }
}
