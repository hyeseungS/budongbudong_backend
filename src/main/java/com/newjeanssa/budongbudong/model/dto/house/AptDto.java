package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AptDto {
    private String aptId;
    private String dongCode;
    private String aptName;
    private String totalDong;
    private String salePrice;
    private String rentPrice;
    private String totalHouseholds;
    private String aptFloor;
    private String approvalDate;
    private String totalParkingLot;
    private String floorAreaRatio;
    private String buildingLandRatio;
    private String constructionCompany;
    private String heatingSystem;
    private String managementOffice;
    private String aptAddress;
    private String lat;
    private String lng;
    private String schoolName;
    private String schoolDist;
    private String schoolAddress;
    private long aptHit;
    private long totalScore;;
    private long interiorScore;
    private long facilitiesScore;

    List<AptAreaDto> aptAreaList;
    List<AptRealTransDto> aptRealTransList;
}
