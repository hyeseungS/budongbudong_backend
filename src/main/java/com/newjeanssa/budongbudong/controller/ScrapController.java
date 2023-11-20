package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import com.newjeanssa.budongbudong.model.service.scrap.ScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/scrap")
@RequiredArgsConstructor
@Api(tags = {"관심매물 API"})
public class ScrapController {
    private final ScrapService scrapService;
    private final UserService userService;

    /*
    관심매물 등록
     */
    @PostMapping("/{aptRealTransId}")
    @ApiOperation(value = "관심매물 등록", notes = "관심매물 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> registerScrap(@PathVariable long aptRealTransId) {

        Optional<UserDto> userDto = userService.getUser();
        scrapService.registerScrap(userDto, aptRealTransId);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    회원의 관심매물 전체 조회
     */
    @GetMapping("")
    @ApiOperation(value = "회원의 관심매물 전체 조회", notes = "회원의 관심매물 전체 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getScraps() {

        Optional<UserDto> userDto = userService.getUser();
        return ResponseEntity.ok(new BaseResponse(scrapService.getScraps(userDto)));
    }

    /*
    관심매물 여부 조회
     */
    @GetMapping("/{aptRealTransId}")
    @ApiOperation(value = "관심매물 여부 조회", notes = "관심매물 id 입력 후 관심매물 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> isScrap(@PathVariable long aptRealTransId) {
        Optional<UserDto> userDto = userService.getUser();
        boolean isScrap = scrapService.isScrap(userDto, aptRealTransId);

        return ResponseEntity.ok(new BaseResponse(isScrap));
    }

    /*
    관심매물 삭제
     */
    @DeleteMapping("/{aptRealTransId}")
    @ApiOperation(value = "관심매물 삭제", notes = "관심매물 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> removeLike(@PathVariable long aptRealTransId) {
        Optional<UserDto> userDto = userService.getUser();
        scrapService.removeScrap(userDto, aptRealTransId);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }
}
