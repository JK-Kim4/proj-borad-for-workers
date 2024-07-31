package com.changbi.tradeunion.boardforworkers.board.presentation;

import com.changbi.tradeunion.boardforworkers.board.exception.AlreadyReportedPostException;
import com.changbi.tradeunion.boardforworkers.board.exception.PostIllegalArgumentException;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BoardControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(BoardControllerAdvice.class);

    @ExceptionHandler(AlreadyReportedPostException.class)
    protected ResponseEntity<ResultDto> handleAlreadyReportedPostException(
            final HttpServletRequest request,
            final AlreadyReportedPostException ex) {

        logger.error("[ERROR-]\t{} \t{} \t{}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        logger.error("[ERROR-POSTID-MEMBERID]\t{} \t{}",ex.getPostId(), ex.getMemberId());

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_FAIL_ALREADY_EXIST)
                        .resultMessage(ex.getErrorMessage())
                .build());
    }

    @ExceptionHandler(PostIllegalArgumentException.class)
    protected ResponseEntity<Object> handlePostIllegalArgumentException(
            final HttpServletRequest request,
            final PostIllegalArgumentException ex) {

        logger.error("[ERROR-]\t{} \t{} \t{}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        logger.error("[ERROR-BOARDID-POSTID]\t{} \t{}",ex.getBoardId(), ex.getPostId());

        return ResponseEntity.ok(ResultDto.builder()
                .resultCode(CommonValues.RESULT_CODE_FAIL_DEFAULT)
                .resultMessage(ex.getErrorMessage())
                .build());

    }
}
