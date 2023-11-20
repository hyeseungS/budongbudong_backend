package com.newjeanssa.budongbudong.model.dto.top;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopDto {
    private String aptId;
    private String dongName;
    private String aptAddress;
    private String aptName;
}
