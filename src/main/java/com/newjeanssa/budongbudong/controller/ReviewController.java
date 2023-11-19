package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.review.ReviewRegisterRequest;
import com.newjeanssa.budongbudong.model.dto.review.ReviewUpdateRequest;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import com.newjeanssa.budongbudong.model.service.review.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.INVALID_INPUT_VALUE;
import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Api(tags = {"리뷰 API"})
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    /*
    리뷰 등록
     */
    @PostMapping("")
    @ApiOperation(value = "리뷰 등록", notes = "리뷰 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> registerReview(@RequestBody ReviewRegisterRequest reviewRegisterRequest) {

        Optional<UserDto> userDto = userService.getUser();
        reviewService.registerReview(userDto, reviewRegisterRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    아파트 아이디로 리뷰 전체 조회
     */
    @GetMapping("/{aptId}")
    @ApiOperation(value = "아파트 아이디로 리뷰 전체 조회", notes = "아파트 아이디 입력 후 공지사항 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getNotices(@PathVariable String aptId) {
        return ResponseEntity.ok(new BaseResponse(reviewService.getReviewsByAptId(aptId)));
    }

    /*
    회원의 리뷰 전체 조회
     */
    @GetMapping("")
    @ApiOperation(value = "회원의 리뷰 전체 조회", notes = "로그인된 회원의 리뷰 전체 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getNotices() {

        Optional<UserDto> userDto = userService.getUser();
        return ResponseEntity.ok(new BaseResponse(reviewService.getReviewsByuserId(userDto)));
    }

    /*
   리뷰 수정
     */
    @PutMapping("")
    @ApiOperation(value = "리뷰 수정", notes = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-리뷰 아이디 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> modifyNotice(@RequestBody ReviewUpdateRequest reviewUpdateRequest) {

        Optional<UserDto> userDto = userService.getUser();
        if(reviewService.getReview(reviewUpdateRequest.getReviewId()).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        reviewService.modifyReview(userDto, reviewUpdateRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    리뷰 삭제
     */
    @DeleteMapping("/{reviewId}")
    @ApiOperation(value = "리뷰 삭제", notes = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-리뷰 아이디 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> removeNotice(@PathVariable Long reviewId) {
        if(reviewService.getReview(reviewId).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        reviewService.removeReview(reviewId);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }
}
