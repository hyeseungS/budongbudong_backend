package com.newjeanssa.budongbudong.model.dto.like;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.house.AptRealTransDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    private Long likeId;
    private AptRealTransDto aptRealTransDto;
    private UserDto userDto;
}
