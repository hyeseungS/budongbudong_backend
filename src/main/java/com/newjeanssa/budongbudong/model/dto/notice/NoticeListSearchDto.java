package com.newjeanssa.budongbudong.model.dto.notice;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListSearchDto {
    private int pageNum;
    private int pageSize;
}
