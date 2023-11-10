package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.config.security.jwt.TokenDto;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignInRequest;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import com.newjeanssa.budongbudong.model.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "로그인/회원가입 API")
public class AuthController {

    private final AuthService authService;
    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD = Pattern.compile("^.*(?=^.{8,}$)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",Pattern.CASE_INSENSITIVE);

    private final long COOKIE_EXPIRATION = 7776000; // 90일

    /*
    회원가입
     */
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "이메일 형식 검사 + 비밀번호 형식(영문 + 특수문자 8자이상)")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2002-이메일형식예외, 2003-비밀번호형식예외, 2004-이미존재하는회원, 2006-미입력칸존재"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> signUp(@Validated @RequestBody UserSignUpRequest userSignUpRequest) {
        if (!isRegexEmail(userSignUpRequest.getEmail())) {
            throw new BaseException(INVALID_EMAIL);
        }
        if (!isRegexPassword(userSignUpRequest.getPassword())) {
            throw new BaseException(INVALID_PASSWORD);
        }
        authService.signUp(userSignUpRequest);
        return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
    }

    /*
    로그인
     */
    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "유저 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2005-로그인실패, 2006-미입력칸존재"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> signIn(@Validated @RequestBody UserSignInRequest userSignInRequest, HttpServletResponse response) {
        // User 등록 및 Refresh Token 저장
        TokenDto tokenDto = authService.signIn(userSignInRequest);
        setToken(response, tokenDto);
        return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
    }

    /*
    검증
     */
    @PostMapping("/validate")
    @Operation(summary = "Access Token 검증", description = "2008 오류 시 재발급 필요")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "2008-Access Token 만료일자 초과 재발급 필요"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> validate(HttpServletRequest request) {
        if (!authService.validate(request)) {
            return ResponseEntity.ok(new BaseResponse<>(SUCCESS)); // 재발급 필요X
        } else {
            throw new BaseException(EXPIRED_ACCESS); // 재발급 필요
        }
    }

    /*
    재발급
     */
    @PostMapping("/reissue")
    @Operation(summary = "재발급", description = "JWT 토큰이 만료일자만 초과한 유효 토큰일 경우 때만 사용 -> AT, RT 재발급")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "2008, 재발급 실패, 재로그인 필요"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<?> refresh(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @CookieValue(name = "refresh-token", required = false) String refreshToken) {
        TokenDto tokenDto = authService.reissue(request, refreshToken);
        if(tokenDto == null) {
            throw new BaseException(INVALID_REFRESH); // 재로그인 필요
        }
        setToken(response, tokenDto);
        return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
    }

    private boolean isRegexEmail(String email) {
        return EMAIL.matcher(email).find();
    }

    private boolean isRegexPassword(String password) {
        return PASSWORD.matcher(password).find();
    }

    // Access Token, Refresh Token 저장
    public void setToken(HttpServletResponse response, TokenDto tokenDto) {
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDto.getAccessToken());
        response.setHeader(HttpHeaders.SET_COOKIE, setRefreshToken(tokenDto.getRefreshToken()).toString());
    }

    // Refresh Token 저장
    public ResponseCookie setRefreshToken(String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refresh-token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .maxAge(COOKIE_EXPIRATION)
                .build();
        return cookie;
    }
}
