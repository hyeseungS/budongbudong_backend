package com.newjeanssa.budongbudong.model.dto.scrap;

import com.newjeanssa.budongbudong.model.dto.house.AptRealTransDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {
    private Long scrapId;
    private AptRealTransDto aptRealTransDto;
}
