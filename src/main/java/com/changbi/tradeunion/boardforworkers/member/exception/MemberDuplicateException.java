package com.changbi.tradeunion.boardforworkers.member.exception;

public class MemberDuplicateException extends RuntimeException{

    private static final String MEMBER_DUPLICATION_ERROR_MESSAGE = "이미 존재하는 회원입니다.";

    public MemberDuplicateException() {
        super(MEMBER_DUPLICATION_ERROR_MESSAGE);
    }
}
