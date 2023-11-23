package com.newjeanssa.budongbudong.model.dto.qa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaUserDto {
    private Long qaId;
    private String qaTitle;
    private String qaContent;
    private String qaComment;
    private boolean commentStatus;
    private LocalDateTime createTime;
}