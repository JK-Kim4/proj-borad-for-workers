package com.changbi.tradeunion.boardforworkers.member.presentation;

import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberDuplicateException;
import com.changbi.tradeunion.boardforworkers.member.exception.MemberValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MemberControllerAdvice {

    @ExceptionHandler(MemberValidationException.class)
    public ResponseEntity<ResultDto> handleMemberValidationException(MemberValidationException e) {

        return ResponseEntity.ok(ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_FAIL_MEMBER_LOGIN_VALIDATION)
                        .resultMessage(e.getErrorMessage())
                .build());
    }

    @ExceptionHandler(MemberDuplicateException.class)
    public ResponseEntity<ResultDto> handleMemberDuplicationException(MemberDuplicateException e){

        return ResponseEntity.ok( ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_FAIL_MEMBER_DUPLICATION)
                        .resultMessage(e.getErrorMessage())
                        .build());
    }

}
