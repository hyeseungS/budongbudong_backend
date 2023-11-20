package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.scrap.ScrapResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScrapDao {
    void insertScrap(Long userId, Long aptRealTransId);
    List<ScrapResponseDto> selectScraps(Long userId);
    List<String> selectDongNames(Long userId);
    Optional<Long> selectScrap(Long userId, Long aptRealTransId);
    void deleteScrap(Long userId, Long aptRealTransId);
}
