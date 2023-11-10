package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.notice.NoticeDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeListSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeDao {
    void insertNotice(NoticeDto noticeDto);
    List<NoticeDto> selectNotices(NoticeListSearchDto noticeListSearchDto);
    Optional<NoticeDto> selectNotice(Long noticeId);
    void updateNotice(NoticeDto noticeDto);
    void deleteNotice(Long noticeId);
}
