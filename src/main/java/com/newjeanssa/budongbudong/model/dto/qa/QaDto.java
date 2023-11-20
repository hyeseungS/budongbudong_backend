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
public class QaDto {
    private Long qaId;
    private String qaTitle;
    private String qaContent;
    private String qaComment;
    private boolean commentStatus;
    private LocalDateTime createTime;
    private UserDto userDto;
}
