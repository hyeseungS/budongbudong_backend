package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.config.security.jwt.TokenDto;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignInRequest;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import com.newjeanssa.budongbudong.model.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = {"로그인/회원가입 API"})
public class AuthController {

    private final AuthService authService;
    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD = Pattern.compile("^.*(?=^.{8,}$)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",Pattern.CASE_INSENSITIVE);

    /*
    회원가입
     */
    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입", notes = "이메일 형식 검사 + 비밀번호 형식(영문 + 특수문자 8자이상)")
//    @ApiResponses({
//            @ApiResponse(responseCode = "400", description = "2002-이메일형식예외, 2003-비밀번호형식예외, 2004-이미존재하는회원, 2006-미입력칸존재"),
//            @ApiResponse(responseCode = "500", description = "서버 예외")
//    })
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

    private boolean isRegexEmail(String email) {
        return EMAIL.matcher(email).find();
    }

    private boolean isRegexPassword(String password) {
        return PASSWORD.matcher(password).find();
    }



    /*
    로그인
     */
    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인", notes = "유저 로그인")
//    @ApiResponses({
//            @ApiResponse(responseCode = "400", description = "2005-로그인실패, 2006-미입력칸존재"),
//            @ApiResponse(responseCode = "500", description = "서버 예외")
//    })
    public ResponseEntity<BaseResponse> signIn(@Validated @RequestBody UserSignInRequest userSignInRequest, HttpServletResponse response) {
        TokenDto tokenDto = authService.signIn(userSignInRequest);
        setToken(response, tokenDto);
        return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
    }

    public void setToken(HttpServletResponse response, TokenDto tokenDto) {
        response.setHeader("Authorization", "Bearer "+tokenDto.getAccessToken());
        response.setHeader("Set-Cookie", setRefreshToken(tokenDto.getRefreshToken()).toString());
    }

    public ResponseCookie setRefreshToken(String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .maxAge(60 * 60 * 24 * 7)  //7일
                .sameSite("None")
                .path("/")
                .build();
        return cookie;
    }

    /*
    재발급
     */
    @PostMapping("/reissue")
    @ApiOperation(value = "재발급", notes = "토큰 재발급")
//    @ApiResponses({
//            @ApiResponse(responseCode = "401", description = "2007-재발급실패"),
//            @ApiResponse(responseCode = "500", description = "서버 예외")
//    })
    public ResponseEntity<BaseResponse> refresh(HttpServletRequest request
                                            , HttpServletResponse response
                                            , @CookieValue(name = "RefreshToken", required = false) String refreshToken) throws IOException {
        TokenDto tokenDto = authService.reissue(refreshToken, request);
        setToken(response, tokenDto);
        return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
    }
}
