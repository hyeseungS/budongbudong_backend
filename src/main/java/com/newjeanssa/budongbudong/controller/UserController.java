package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import com.newjeanssa.budongbudong.model.dto.auth.UserUpdateRequest;
import com.newjeanssa.budongbudong.model.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    /*
    회원정보 조회
     */
    @GetMapping("/mypage")
    @ApiOperation(value = "회원정보 조회", notes = "이메일로 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getUser() {
        String email = authService.getUserEmail();
        return ResponseEntity.ok(new BaseResponse(authService.getUserByEmail(email)));
    }

    /*
    회원정보 수정
     */
    @PutMapping("/mypage")
    @ApiOperation(value = "회원정보 수정", notes = "")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> updateUser(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        String email = authService.getUserEmail();
        authService.updateUser(email, userUpdateRequest);
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    회원정보 삭제
     */
    @DeleteMapping("/mypage")
    @ApiOperation(value = "회원정보 삭제", notes = "")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> deleteUser() {
        String email = authService.getUserEmail();
        authService.deleteUser(email);
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    로그아웃
     */
    @PostMapping("/log-out")
    @ApiOperation(value = "로그아웃", notes = "redis 토큰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

}