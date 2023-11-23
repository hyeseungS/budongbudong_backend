package com.newjeanssa.budongbudong.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreSizeDto {
    public String storeId;
    public String distance;
}
