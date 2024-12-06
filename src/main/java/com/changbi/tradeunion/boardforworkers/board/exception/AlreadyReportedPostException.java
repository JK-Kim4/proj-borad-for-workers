package com.changbi.tradeunion.boardforworkers.board.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlreadyReportedPostException extends RuntimeException{

    private Long postId;
    private Long memberId;
    private String errorMessage;


    public AlreadyReportedPostException(Long postId, Long memberId, String message) {
        this.postId = postId;
        this.memberId = memberId;
        this.errorMessage = message;
    }

}
