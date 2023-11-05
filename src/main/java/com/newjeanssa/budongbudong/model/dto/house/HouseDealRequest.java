package com.newjeanssa.budongbudong.model.dto.house;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseDealRequest {
    @NotEmpty
    private String sidoName;

    @NotEmpty
    private String gugunName;

    @NotEmpty
    private String dongName;

    @NotNull
    private int dealYear;

    @NotNull
    private int dealMonth;
}
