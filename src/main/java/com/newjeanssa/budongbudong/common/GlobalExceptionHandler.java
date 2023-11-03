package com.newjeanssa.budongbudong.common;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getStatus().getHttpStatus()).body(new BaseResponse<>(e.getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(FAIL_LOGIN.getHttpStatus()).body(new BaseResponse<>(FAIL_LOGIN));
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(EMPTY_INPUT_VALUE.getHttpStatus()).body(new BaseResponse<>(EMPTY_INPUT_VALUE));
    }

}
