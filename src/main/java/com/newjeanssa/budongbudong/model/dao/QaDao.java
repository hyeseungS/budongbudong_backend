package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.qa.CommentDto;
import com.newjeanssa.budongbudong.model.dto.qa.QaDto;
import com.newjeanssa.budongbudong.model.dto.qa.QaListSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QaDao {
    void insertQa(QaDto qaDto);
    List<QaDto> selectQas(QaListSearchDto qaListSearchDto);
    Optional<QaDto> selectQa(Long qaId);
    void updateQa(QaDto qaDto);
    void deleteQa(Long qaId);
    void insertComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(Long qaId);
}
