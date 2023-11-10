package com.newjeanssa.budongbudong.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeListSearchDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeRegisterRequest;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeUpdateRequest;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import com.newjeanssa.budongbudong.model.service.notice.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
@Api(tags = {"공지사항 API"})
public class NoticeController {

    private final NoticeService noticeService;
    private final UserService userService;

    /*
    공지사항 등록
     */
    @PostMapping("")
    @ApiOperation(value = "공지사항 등록", notes = "공지사항 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> registerNotice(@RequestBody NoticeRegisterRequest noticeRegisterRequest) {

        Optional<UserDto> userDto = userService.getUser();
        noticeService.registerNotice(userDto, noticeRegisterRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    공지사항 전체 조회
     */
    @GetMapping("")
    @ApiOperation(value = "공지사항 전체 조회", notes = "페이지 번호, 페이지 개수 입력 후 공지사항 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getNotices(@ModelAttribute NoticeListSearchDto noticeListSearchDto) {

        if (noticeListSearchDto.getPageNum() <= 0 || noticeListSearchDto.getPageSize() <= 0) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }

        PageHelper.startPage(noticeListSearchDto);

        return ResponseEntity.ok(new BaseResponse(PageInfo.of(noticeService.getNotices(noticeListSearchDto))));
    }

    /*
    공지사항 상세 조회
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "공지사항 상세 조회", notes = "공지사항 아이디 입력 후 공지사항 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getNotices(@RequestParam Long id) {

        Optional<NoticeDto> noticeDto = noticeService.getNotice(id);
        if(noticeDto.isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        return ResponseEntity.ok(new BaseResponse(noticeDto));
    }

    /*
    공지사항 수정
     */
    @PutMapping("")
    @ApiOperation(value = "공지사항 수정", notes = "공지사항 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> modifyNotice(@RequestBody NoticeUpdateRequest noticeUpdateRequest) {

        Optional<UserDto> userDto = userService.getUser();
        if(noticeService.getNotice(noticeUpdateRequest.getNoticeId()).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        noticeService.modifyNotice(userDto, noticeUpdateRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    공지사항 삭제
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> removeNotice(@RequestParam Long id) {

        noticeService.removeNotice(id);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }
}
