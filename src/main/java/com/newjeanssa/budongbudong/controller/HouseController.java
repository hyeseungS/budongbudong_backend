package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.house.HouseRequest;
import com.newjeanssa.budongbudong.model.service.house.HouseService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;


@Slf4j
@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
@Api(tags = {"실거래 검색 API"})
public class HouseController {

    private final HouseService houseService;

    /*
    시도, 구군, 동 조회
     */
    @GetMapping("")
    @Operation(summary = "시도, 구군, 동으로 아파트 리스트 조회", description = "시도, 구군, 동 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getHouseList(@Validated @ModelAttribute HouseRequest houseRequest) {
        return ResponseEntity.ok(new BaseResponse(houseService.getHouseList(houseRequest)));
    }

    /*
    아파트 코드로 조회
     */
    @GetMapping("/{aptcode}")
    @Operation(summary = "아파트코드로 조회", description = "아파트 코드 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getHouseDetailList(@PathVariable String aptcode) {
        return ResponseEntity.ok(new BaseResponse(houseService.getHouseDetail(aptcode)));
    }

    /*
    비교분석 : 매물 id, 비교 개수로 조회
     */
    @GetMapping("/compare")
    @Operation(summary = "비교분석 조회", description = "매물 id 리스트 입력")

    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })

    public ResponseEntity<BaseResponse> getHouseCompareList(@Parameter(name = "dealList", in = QUERY, array = @ArraySchema( schema = @Schema(type = "int")))  @RequestParam List<Integer> dealList) {
        return ResponseEntity.ok(new BaseResponse(houseService.getHouseCompareList(dealList)));
    }
}
