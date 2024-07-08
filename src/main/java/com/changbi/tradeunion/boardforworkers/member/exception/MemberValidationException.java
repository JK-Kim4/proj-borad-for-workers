package com.changbi.tradeunion.boardforworkers.member.exception;

public class MemberValidationException extends RuntimeException{

    private String errorMessage;

    public MemberValidationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
