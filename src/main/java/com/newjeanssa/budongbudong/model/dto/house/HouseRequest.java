package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseRequest {
    @NotEmpty
    private String sidoName;

    @NotEmpty
    private String gugunName;

    @NotEmpty
    private String dongName;
}
