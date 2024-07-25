package com.changbi.tradeunion.boardforworkers.board.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import lombok.Getter;

@Getter
public class PostIllegalArgumentException extends RuntimeException {

    private String errorMessage;
    private Long boardId;
    private Long postId;

    public PostIllegalArgumentException(){
        this.errorMessage = CommonValues.RESULT_MESSAGE_FAIL_POST_ILLEGAL_ARGUMENT;
    }

    public PostIllegalArgumentException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public PostIllegalArgumentException(String errorMessage, Long boardId) {
        this.errorMessage = errorMessage;
        this.boardId = boardId;
    }

    public PostIllegalArgumentException(String errorMessage, Long boardId, Long postId) {
        this.errorMessage = errorMessage;
        this.boardId = boardId;
        this.postId = postId;
    }

}
