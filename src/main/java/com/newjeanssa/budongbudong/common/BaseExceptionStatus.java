package com.newjeanssa.budongbudong.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseExceptionStatus {

    // 성공
    SUCCESS(HttpStatus.OK, 1000, "요청 성공"),
    // 인증
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED, 2000, "토큰 검증 실패"),
    SC_FORBIDDEN(HttpStatus.FORBIDDEN, 2001, "권한 없음"),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, 2002, "이메일 형식을 확인해주세요"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 2003, "비밀번호 형식을 확인해주세요"),
    EXIST_ACCOUNT(HttpStatus.BAD_REQUEST, 2004, "이미 존재하는 회원입니다"),
    FAIL_LOGIN(HttpStatus.BAD_REQUEST, 2005, "로그인 실패"),
    EMPTY_INPUT_VALUE(HttpStatus.BAD_REQUEST, 2006, "값을 모두 입력해주세요"),
    EXPIRED_ACCESS(HttpStatus.UNAUTHORIZED, 2007, "Access Token 만료일자 초과 재발급 필요"),
    INVALID_REFRESH(HttpStatus.UNAUTHORIZED, 2008, "재발급 실패, 재로그인 필요");

    private HttpStatus httpStatus;
    private final int code;
    private final String message;

    private BaseExceptionStatus(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
