package com.newjeanssa.budongbudong.model.dto.notice;

import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createTime;
    private UserDto userDto;

}
