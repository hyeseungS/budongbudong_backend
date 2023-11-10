package com.newjeanssa.budongbudong.model.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateRequest {

    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;

}
