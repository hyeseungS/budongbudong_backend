package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.house.AptCodeDto;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import com.newjeanssa.budongbudong.model.dto.house.HouseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseDao {
    List<AptCodeDto> selectHouseList(HouseRequest houseRequest);
    void updateHit(String aptCode);
    AptDto selectHouseDetail(String aptCode);
    List<AptDto> selectHouseCompareList(List<Integer> dealList);
}
