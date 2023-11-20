package com.newjeanssa.budongbudong.model.dto.qa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaListSearchDto {
    private int pageNum;
    private int pageSize;
}
