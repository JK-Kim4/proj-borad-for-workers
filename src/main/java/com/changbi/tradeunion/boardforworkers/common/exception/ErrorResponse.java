package com.changbi.tradeunion.boardforworkers.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class ErrorResponse {

    private static final Logger logger = LoggerFactory.getLogger(ErrorResponse.class);
    private String errorMessage;

    @Builder
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse from
            (final Exception e) {

        logger.error("[ERROR-NOT FOUND]\t{}", e.getMessage());

        return ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .build();
    }

    public static ErrorResponse from(
            final HttpServletRequest request,
            final Exception exception) {
        logger.error("[ERROR-]\t{}\t{}\t{}", request.getMethod(), request.getRequestURI(), exception.getMessage());
        logger.error("[ERROR RESPONSE INFO]",exception);

        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .build();
    }

}
