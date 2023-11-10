package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserUpdateRequest;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Api(tags = {"마이페이지 API"})
public class UserController {

    private final UserService userService;

    /*
    회원정보 조회
     */
    @GetMapping("/mypage")
    @ApiOperation(value = "회원정보 조회", notes = "토큰으로 회원 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getUser() {
        return ResponseEntity.ok(new BaseResponse(userService.getUser()));
    }

    /*
    회원정보 수정
     */
    @PutMapping("/mypage")
    @ApiOperation(value = "회원정보 수정", notes = "수정할 이름, 비밀번호 입력 후 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> updateUser(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userUpdateRequest);
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    회원정보 삭제
     */
    @DeleteMapping("/mypage")
    @ApiOperation(value = "회원정보 삭제", notes = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    로그아웃
     */
    @PostMapping("/log-out")
    @ApiOperation(value = "로그아웃", notes = "redis 토큰 삭제, 쿠키 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

}