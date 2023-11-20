package com.newjeanssa.budongbudong.model.dto.qa;

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
public class QaRegisterRequest {
    private String qaTitle;
    private String qaContent;
    private UserDto userDto;
}