//package com.changbi.tradeunion.boardforworkers.common.exception;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.resource.NoResourceFoundException;
//
//@ControllerAdvice
//public class NotFoundExceptionHandler {
//
//    @ExceptionHandler(value = {NoHandlerFoundException.class, NoResourceFoundException.class})
//    protected ResponseEntity<ErrorResponse> notFoundException(
//            final HttpServletRequest request,
//            final NoResourceFoundException exception){
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ErrorResponse.from(request, exception));
//    }
//}
