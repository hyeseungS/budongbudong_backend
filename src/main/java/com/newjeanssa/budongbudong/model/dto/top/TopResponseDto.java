package com.newjeanssa.budongbudong.model.dto.top;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopResponseDto {
    private String dongName;
    private List<TopDto> topDtoList;
}
