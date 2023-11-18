package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.scrap.ScrapDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScrapDao {
    void insertScrap(Long userId, Long aptRealTransId);
    List<ScrapDto> selectScraps(Long userId);
    Optional<Long> selectScrap(Long userId, Long aptRealTransId);
    void deleteScrap(Long userId, Long aptRealTransId);
}
