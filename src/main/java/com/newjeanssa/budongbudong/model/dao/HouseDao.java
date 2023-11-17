package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.house.AptCodeDto;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import com.newjeanssa.budongbudong.model.dto.house.HouseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseDao {
    List<AptCodeDto> findHouseList(HouseRequest houseRequest);
    void updateHit(String aptCode);
    AptDto findHouseDetail(String aptCode);
    List<AptDto> findHouseCompareList(List<Integer> dealList);
}
