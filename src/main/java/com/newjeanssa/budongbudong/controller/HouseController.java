package com.newjeanssa.budongbudong.controller;

import com.newjeanssa.budongbudong.common.BaseResponse;
import com.newjeanssa.budongbudong.model.dto.auth.UserSignUpRequest;
import com.newjeanssa.budongbudong.model.dto.house.HouseDealRequest;
import com.newjeanssa.budongbudong.model.service.auth.AuthService;
import com.newjeanssa.budongbudong.model.service.house.HouseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    /*
    시도, 구군, 동, 거래년도, 거래월 조회
     */
    @GetMapping("/")
    @ApiOperation(value = "시도, 구군, 동, 거래년도, 거래월 조회", notes = "시도, 구군, 동, 거래년도, 거래 월 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "500", description = "서버 예외")
    })
    public ResponseEntity<BaseResponse> getHouseDealList(@Validated @ModelAttribute HouseDealRequest houseDealRequest) {
        return ResponseEntity.ok(new BaseResponse(houseService.findHouseDeals(houseDealRequest)));
    }
}
