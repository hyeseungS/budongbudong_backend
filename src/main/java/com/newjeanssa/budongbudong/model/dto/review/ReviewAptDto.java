package com.newjeanssa.budongbudong.model.dto.review;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAptDto {
    private Long reviewId;
    private String aptId;
    private String reviewComment;
    private double reviewScore;
    private LocalDateTime createTime;
    private UserDto userDto;
}
