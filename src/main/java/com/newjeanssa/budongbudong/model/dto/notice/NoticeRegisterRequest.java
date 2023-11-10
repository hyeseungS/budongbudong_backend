package com.newjeanssa.budongbudong.model.dto.notice;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRegisterRequest {

    private String noticeTitle;
    private String noticeContent;

}