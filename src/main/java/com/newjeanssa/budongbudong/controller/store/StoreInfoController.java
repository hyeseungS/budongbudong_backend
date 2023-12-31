package com.newjeanssa.budongbudong.controller.store;

import com.newjeanssa.budongbudong.common.BaseExceptionStatus;
import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import com.newjeanssa.budongbudong.model.service.house.HouseService;
import com.newjeanssa.budongbudong.model.service.store.StoreInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
@Api(tags = {"상권 API"})
public class StoreInfoController {

    private final StoreInfoService storeInfoService;
    private final HouseService houseService;

    /*
    상권 전체 조회
     */
    @GetMapping("/{dongCode}/{categoryCode}")
    @ApiOperation(value = "상권 전체 조회", notes = "상권 전체 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-상권 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getStoreInfos(@PathVariable String dongCode, @PathVariable String categoryCode) {

        return ResponseEntity.ok(new BaseResponse(storeInfoService.getStoreInfos(dongCode, categoryCode)));
    }

    /*
    상권 점수
     */
    @GetMapping("/score")
    @ApiOperation(value = "상권 점수 계산", notes = "상권 전체 계산")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "2009-상권 정보 입력 오류"),
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> calculateStoreScore() {
        List<AptDto> aptDtoList = houseService.getHouses();
        storeInfoService.modifyScore(aptDtoList);
        return ResponseEntity.ok(new BaseResponse(BaseExceptionStatus.SUCCESS));
    }
}
