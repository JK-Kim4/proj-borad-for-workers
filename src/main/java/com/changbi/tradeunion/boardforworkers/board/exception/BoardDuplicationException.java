package com.changbi.tradeunion.boardforworkers.board.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;

public class BoardDuplicationException extends RuntimeException{

    private String errorMessage;
    private Long boardId;

    public BoardDuplicationException(){
        this.errorMessage = CommonValues.RESULT_MESSAGE_FAIL_BOARD_NAME_DUPLICATION;
    }

    public BoardDuplicationException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public BoardDuplicationException(String errorMessage, Long boardId) {
        this.errorMessage = errorMessage;
        this.boardId = boardId;
    }
}
