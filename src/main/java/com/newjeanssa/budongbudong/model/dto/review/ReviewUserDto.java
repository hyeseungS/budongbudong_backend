package com.newjeanssa.budongbudong.model.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUserDto {
    private Long reviewId;
    private String aptId;
    private String aptName;
    private String aptAddress;
    private String aptDong;
    private String reviewComment;
    private double reviewScore;
    private LocalDateTime createTime;
}
