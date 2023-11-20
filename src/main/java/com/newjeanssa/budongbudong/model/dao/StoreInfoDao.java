package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.store.StoreInfoDto;
import com.newjeanssa.budongbudong.model.dto.store.StoreInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreInfoDao {
    void insertAll(List<StoreInfoDto> storeInfoDtoList);
    List<StoreInfoResponseDto> selectByCategoryCode(String categoryCode);
}
