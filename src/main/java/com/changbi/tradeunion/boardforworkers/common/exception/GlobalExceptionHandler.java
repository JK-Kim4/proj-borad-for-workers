package com.changbi.tradeunion.boardforworkers.common.exception;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Order(value = 2)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.from(ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.from(ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return ResponseEntity.ok(
                ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_FAIL_DEFAULT)
                        .resultMessage(CommonValues.RESULT_MESSAGE_FAIL_DEFAULT)
                    .build());
    }

    @ExceptionHandler(value = {NoResultException.class, NonUniqueResultException.class})
    protected ResponseEntity<ResultDto> handleNoResultException(
            final HttpServletRequest request,
            final PersistenceException ex) {

        logger.error("[ERROR-]\t{} \t{} \t{}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        logger.error("{}", ex.getStackTrace());

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_FAIL_SINGLE_ENTITY_VIOLATION)
                        .resultMessage(CommonValues.RESULT_MESSAGE_FAIL_SINGLE_ENTITY_VIOLATION)
                .build());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(
            final HttpServletRequest request,
            final Exception exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.from(request, exception));
    }
}
