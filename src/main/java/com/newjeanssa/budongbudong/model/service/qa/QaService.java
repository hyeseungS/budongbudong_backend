package com.newjeanssa.budongbudong.model.service.qa;

import com.newjeanssa.budongbudong.model.dao.QaDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.qa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QaService {

    private final QaDao qaDao;

    /*
    1:1문의 등록
     */
    public void registerQa(Optional<UserDto> userDto, QaRegisterRequest qaRegisterRequest) {
        QaDto qaDto = QaDto.builder()
                .qaTitle(qaRegisterRequest.getQaTitle())
                .qaContent(qaRegisterRequest.getQaContent())
                .build();
        userDto.ifPresent(qaDto::setUserDto);
        qaDao.insertQa(qaDto);
    }

    /*
    1:1문의 전체 조회
     */
    public List<QaDto> getQas(QaListSearchDto qaListSearchDto) {
        return qaDao.selectQas(qaListSearchDto);
    }

    /*
    1:1문의 상세 조회
     */
    public Optional<QaDto> getQa(Long qaId) {
        return qaDao.selectQa(qaId);
    }

    /*
    1:1문의 내용 수정
     */
    public void modifyQa(Optional<UserDto> userDto, QaUpdateRequest qaUpdateRequest) {
        QaDto qaDto = QaDto.builder()
                .qaId(qaUpdateRequest.getQaId())
                .qaTitle(qaUpdateRequest.getQaTitle())
                .qaContent(qaUpdateRequest.getQaContent())
                .build();
        userDto.ifPresent(qaDto::setUserDto);
        qaDao.updateQa(qaDto);
    }

    /*
    1:1문의 삭제
     */
    public void removeQa(Long qaId) {
        qaDao.deleteQa(qaId);
    }

    /*
    1:1문의 내용 답변
     */
    public void commentQa(CommentDto commentDto) {
        qaDao.insertComment(commentDto);
    }

    /*
    1:1문의 내용 답변 수정
     */
    public void modifyComment(CommentDto commentDto) {
        qaDao.updateComment(commentDto);
    }

    /*
    1:1문의 답변 삭제
     */
    public void removeComment(Long qaId) {
        qaDao.deleteComment(qaId);
    }
}
