package com.newjeanssa.budongbudong.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDistanceDto {
    private String categoryCode;
    private String dongCode;
    private int start;
    private int end;
    private double lng;
    private double lat;
}
