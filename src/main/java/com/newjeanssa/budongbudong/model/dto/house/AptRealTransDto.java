package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AptRealTransDto {
    private long aptRealTransId;
    private String aptId;
    private String aptRealTransName;
    private String aptRealTransPrice;
    private String aptRealTransImg;
    private String aptRealTransFloor;
    private String aptRealTransDir;
    private String aptRealTransArea;
    private boolean isScrap;
}
