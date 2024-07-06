package com.changbi.tradeunion.boardforworkers.member.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;

public class MemberDuplicateException extends RuntimeException{

    private String errorMessage;

    public MemberDuplicateException() {
        this.errorMessage = CommonValues.RESULT_MESSAGE_FAIL_MEMBER_DUPLICATION;
    }

    public MemberDuplicateException(String message){
        this.errorMessage = message;
    }
}
