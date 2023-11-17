package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AptRealHistoryDto {
    private long aptRealHistoryId;
    private long aptAreaId;
    private String aptArea;
    private String aptId;
    private String aptRealHistoryMonth;
    private String aptRealHistoryType;
    private String aptRealHistoryPrice;
}
