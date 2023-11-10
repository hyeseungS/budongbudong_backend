package com.newjeanssa.budongbudong.model.service.notice;

import com.newjeanssa.budongbudong.model.dao.NoticeDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeListSearchDto;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeRegisterRequest;
import com.newjeanssa.budongbudong.model.dto.notice.NoticeUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    /*
    공지사항 등록
     */
    public void registerNotice(Optional<UserDto> userDto, NoticeRegisterRequest noticeRequest) {
        NoticeDto noticeDto = NoticeDto.builder()
                .noticeTitle(noticeRequest.getNoticeTitle())
                .noticeContent(noticeRequest.getNoticeContent())
                .build();
        userDto.ifPresent(noticeDto::setUserDto);
        noticeDao.insertNotice(noticeDto);
    }

    /*
    공지사항 전체 조회
     */
    public List<NoticeDto> getNotices(NoticeListSearchDto noticeListSearchDto) {
        return noticeDao.selectNotices(noticeListSearchDto);
    }

    /*
    공지사항 상세 조회
     */
    public Optional<NoticeDto> getNotice(Long id) {
        return noticeDao.selectNotice(id);
    }

    /*
    공지사항 수정
     */
    public void modifyNotice(Optional<UserDto> userDto, NoticeUpdateRequest noticeUpdateRequest) {
        NoticeDto noticeDto = NoticeDto.builder()
                .noticeId(noticeUpdateRequest.getNoticeId())
                .noticeTitle(noticeUpdateRequest.getNoticeTitle())
                .noticeContent(noticeUpdateRequest.getNoticeContent())
                .build();
        userDto.ifPresent(noticeDto::setUserDto);
        noticeDao.updateNotice(noticeDto);
    }

    /*
    공지사항 삭제
     */
    public void removeNotice(Long noticeId) {
        noticeDao.deleteNotice(noticeId);
    }
}
