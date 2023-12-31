package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AptCodeDto {
    private String aptCode;
    private String aptName;
    private String aptAddress;
    private String lat;
    private String lng;
}
