package com.changbi.tradeunion.boardforworkers.member.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;

public class MemberNotFountException extends RuntimeException{


    private String errorMessage;

    public MemberNotFountException() {
        super(CommonValues.RESULT_MESSAGE_FAIL_MEMBER_NOTFOUND);
    }

    public MemberNotFountException(String message){
        this.errorMessage = message;
    }
}
