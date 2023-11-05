package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseInfoDto {
    private long aptCode;
    private String roadName;
    private String dong;
    private String apartmentName;
    private String lng;
    private String lat;
}
