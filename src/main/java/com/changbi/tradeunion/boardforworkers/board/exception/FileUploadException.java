package com.changbi.tradeunion.boardforworkers.board.exception;

import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException{

    private String errorMessage;
    private String fileName;

    public FileUploadException(String errorMessage, String fileName){
        this.errorMessage = errorMessage;
        this.fileName = fileName;
    }

    public FileUploadException(String errorMessage){
        this.errorMessage = errorMessage;
    }

}
