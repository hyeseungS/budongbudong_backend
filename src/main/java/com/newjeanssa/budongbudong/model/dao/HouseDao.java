package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.house.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseDao {
    List<AptCodeDto> selectHouseList(HouseRequest houseRequest);
    void updateHit(String aptCode);
    AptDto selectHouseDetail(String aptCode);
    List<AptRealTransDto> selectAptRealTrans(AptIdsDto aptIdsDto);
    List<AptDto> selectHouseCompareList(List<Integer> dealList);
}
