package com.changbi.tradeunion.boardforworkers.common.exception;

import com.changbi.tradeunion.boardforworkers.board.exception.PrivateBoardAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Order(value = 1)
@ControllerAdvice
public class GlobalHtmlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalHtmlExceptionHandler.class);

    @ExceptionHandler(value = IllegalAccessException.class)
    protected String  handleIllegalAccessException(
            final HttpServletRequest request,
            final IllegalAccessException ex) throws IOException {

        logger.error("[ERROR-]\t{} \t{} \t{}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        logger.error("{}", ex);

        return "error/401";
    }

    @ExceptionHandler(value = PrivateBoardAuthorizationException.class)
    protected String  handlePrivateBoardAuthorizationException(
            final HttpServletRequest request,
            final PrivateBoardAuthorizationException ex) throws IOException {

        logger.error("[ERROR-]\t{} \t{} \t{}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        logger.error("[ERROR-REQUEST BOARD ID] \t{}", ex.getBoardId());

        return "error/403";
    }
}
