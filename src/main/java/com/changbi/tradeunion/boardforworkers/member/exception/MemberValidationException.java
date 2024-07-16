package com.changbi.tradeunion.boardforworkers.member.exception;

import lombok.Getter;

@Getter
public class MemberValidationException extends RuntimeException{

    private String errorMessage;

    public MemberValidationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
