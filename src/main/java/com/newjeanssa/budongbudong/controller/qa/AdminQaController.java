package com.newjeanssa.budongbudong.controller.qa;

import com.newjeanssa.budongbudong.common.BaseException;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.qa.*;
import com.newjeanssa.budongbudong.model.service.qa.QaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.INVALID_INPUT_VALUE;
import static com.newjeanssa.budongbudong.common.BaseExceptionStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/admin/qa")
@RequiredArgsConstructor
@Api(tags = {"관리자 1:1문의 답변 API"})
public class AdminQaController {
    private final QaService qaService;
    /*
    1:1 문의 답변
     */
    @PostMapping("")
    @ApiOperation(value = "1:1문의 답변", notes = "1:1문의 답변")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> registerQa(@RequestBody CommentDto commentDto) {

        qaService.commentQa(commentDto);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    1:1 문의 답변 수정
     */
    @PutMapping("")
    @ApiOperation(value = "1:1 문의 답변 수정", notes = "1:1 문의 답변 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getQas(@RequestBody CommentDto commentDto) {
        if(qaService.getQa(commentDto.getQaId()).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        qaService.modifyComment(commentDto);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

    /*
    1:1 문의 답변 삭제
     */
    @DeleteMapping("/{qaId}")
    @ApiOperation(value = "1:1 문의 답변 삭제", notes = "1:1 문의 답변 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> removeQa(@PathVariable Long qaId) {
        if(qaService.getQa(qaId).isEmpty()) {
            throw new BaseException(INVALID_INPUT_VALUE);
        }
        qaService.removeComment(qaId);

        return ResponseEntity.ok(new BaseResponse(SUCCESS));
    }

}
