package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.store.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreInfoDao {
    void insertAll(List<StoreInfoDto> storeInfoDtoList);
    List<StoreInfoResponseDto> selectByCategoryCode(StoreInfoRequestDto storeInfoRequestDto);
    List<StoreSizeDto> getDistanceCount(StoreDistanceDto storeDistanceDto);
    void updateStoreScore(ScoreDto scoreDto);
}
