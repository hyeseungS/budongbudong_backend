package com.newjeanssa.budongbudong.model.dto.review;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String reviewComment;
    private int reviewScore;
    private LocalDateTime createTime;
    private AptDto aptDto;
    private UserDto userDto;
}
