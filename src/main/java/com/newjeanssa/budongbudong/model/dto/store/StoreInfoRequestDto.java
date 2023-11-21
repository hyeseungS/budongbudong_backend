package com.newjeanssa.budongbudong.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoRequestDto {
    private String dongCode;
    private String categoryCode;
}
