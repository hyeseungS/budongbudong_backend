package com.newjeanssa.budongbudong.model.dto.scrap;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapResponseDto {
    private String dongName;
    private List<ScrapDto> scrapDtoList;

}
