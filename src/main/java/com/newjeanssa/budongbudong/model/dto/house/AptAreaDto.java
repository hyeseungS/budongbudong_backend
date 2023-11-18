package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AptAreaDto {
    private int aptAreaId;
    private String aptArea;
    private String aptId;
    private String aptAreaImg;
    private String aptAreaSupplyExclusive;
    private String totalRoom;
    private String aptAreaTotalHouseholds;
    private String frontDoorStructure;
    private String price;
    private String aptAreaRealTrans;
    private String administrationCost;
    private String holdingTax;
    private String upperLimitSale;
    private String lowerLimitSale;
    private String upperLimitRent;
    private String lowerLimitRent;
    private String saleCompRent;
    private List<AptRealHistoryDto> aptRealHistoryList;
}
