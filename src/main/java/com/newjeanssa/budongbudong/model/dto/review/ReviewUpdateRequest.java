package com.newjeanssa.budongbudong.model.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {
    private Long reviewId;
    private String reviewComment;
    private double reviewScore;

}
