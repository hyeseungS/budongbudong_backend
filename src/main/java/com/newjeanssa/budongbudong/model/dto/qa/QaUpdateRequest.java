package com.newjeanssa.budongbudong.model.dto.qa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaUpdateRequest {
    private Long qaId;
    private String qaTitle;
    private String qaContent;
}
