package com.newjeanssa.budongbudong.model.dto.qa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaRegisterRequest {
    private String qaTitle;
    private String qaContent;
}