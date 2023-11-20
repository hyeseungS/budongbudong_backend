package com.newjeanssa.budongbudong.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoResponseDto {
    private Long storeId;
    private String storeName;
    private String dongCode;
    private String streetAddress;
    private String lng;
    private String lat;
    private CategoryInfoDto categoryInfoDto;
}
