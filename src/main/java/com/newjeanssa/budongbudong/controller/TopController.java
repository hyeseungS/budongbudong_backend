package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import com.newjeanssa.budongbudong.model.service.top.TopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/top")
@RequiredArgsConstructor
@Api(tags = {"Top 조회 API"})
public class TopController {
    private final TopService topService;
    private final UserService userService;

    /*
    Top 조회순 5개 조회
     */
    @GetMapping("")
    @ApiOperation(value = "Top 조회순 5개 조회", notes = "Top 조회순 5개 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getTop() {
        return ResponseEntity.ok(new BaseResponse(topService.getTop()));
    }

    /*
    회원 관심 매물 중 랜덤 동 Top 조회순 5개 조회
     */
    @GetMapping("/user")
    @ApiOperation(value = "회원 관심 매물 중 랜덤 동 Top 조회순 5개 조회", notes = "회원 관심 매물 중 랜덤 동 Top 조회순 5개 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getTopScrap() {
        Optional<UserDto> userDto = userService.getUser();
        return ResponseEntity.ok(new BaseResponse(topService.getTopScrap(userDto)));
    }
}
