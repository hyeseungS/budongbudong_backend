package com.newjeanssa.budongbudong.model.dto.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AptIdsDto {
    private long userId;
    private String aptId;
}
