package com.newjeanssa.budongbudong.controller.qa;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.qa.QaDto;
import com.newjeanssa.budongbudong.model.dto.qa.QaListSearchDto;
import com.newjeanssa.budongbudong.model.dto.qa.QaRegisterRequest;
import com.newjeanssa.budongbudong.model.dto.qa.QaUpdateRequest;
import com.newjeanssa.budongbudong.model.service.auth.UserService;
import com.newjeanssa.budongbudong.model.service.qa.QaService;
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
@RequestMapping("/api/qa")
@RequiredArgsConstructor
@Api(tags = {"1:1문의 API"})
public class QaController {

    private final UserService userService;
    private final QaService qaService;
    /*
    1:1 문의 등록
     */
    @PostMapping("")
    @ApiOperation(value = "1:1문의 등록", notes = "1:1문의 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> registerQa(@RequestBody QaRegisterRequest qaRegisterRequest) {

        Optional<UserDto> userDto = userService.getUser();
        qaService.registerQa(userDto, qaRegisterRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    1:1 문의 전체 조회
     */
    @GetMapping("")
    @ApiOperation(value = "1:1 문의 전체 조회", notes = "페이지 번호, 페이지 개수 입력 후 1:1 문의 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-페이지 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getQas(@ModelAttribute QaListSearchDto QaListSearchDto) {

        if (QaListSearchDto.getPageNum() <= 0 || QaListSearchDto.getPageSize() <= 0) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }

        PageHelper.startPage(QaListSearchDto);

        return ResponseEntity.ok(new BaseResponse(PageInfo.of(qaService.getQas(QaListSearchDto))));
    }

    /*
     1:1 문의 상세 조회
     */
    @GetMapping("/{qaId}")
    @ApiOperation(value = "1:1 문의 상세 조회", notes = "1:1 문의 아이디 입력 후 공지사항 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getQas(@RequestParam Long qaId) {

        Optional<QaDto> qaDto = qaService.getQa(qaId);
        if(qaDto.isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        return ResponseEntity.ok(new BaseResponse(qaDto));
    }

    /*
    1:1 문의 수정
     */
    @PutMapping("")
    @ApiOperation(value = "1:1 문의 수정", notes = "1:1 문의 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> modifyQa(@RequestBody QaUpdateRequest qaUpdateRequest) {

        Optional<UserDto> userDto = userService.getUser();
        if(qaService.getQa(qaUpdateRequest.getQaId()).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        qaService.modifyQa(userDto, qaUpdateRequest);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    1:1 문의 삭제
     */
    @DeleteMapping("/{qaId}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> removeQa(@RequestParam Long qaId) {

        qaService.removeQa(qaId);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }
}
