package com.changbi.tradeunion.boardforworkers.application.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import lombok.Getter;

@Getter
public class MetaException extends RuntimeException {

    private String errorMessage;

    public MetaException() {
        super(CommonValues.RESULT_CODE_FAIL_SYSTEM);
    }

    public MetaException(String message){
        this.errorMessage = message;
    }
}
