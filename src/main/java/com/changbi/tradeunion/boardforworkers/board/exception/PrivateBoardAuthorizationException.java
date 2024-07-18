package com.changbi.tradeunion.boardforworkers.board.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import lombok.Getter;

@Getter
public class PrivateBoardAuthorizationException extends RuntimeException{

    private String errorMessage;
    private Long boardId;

    public PrivateBoardAuthorizationException(){
        this.errorMessage = CommonValues.RESULT_MESSAGE_FAIL_BOARD_AUTHORIZATION;
    }

    public PrivateBoardAuthorizationException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public PrivateBoardAuthorizationException(String errorMessage, Long boardId) {
        this.errorMessage = errorMessage;
        this.boardId = boardId;
    }
}
