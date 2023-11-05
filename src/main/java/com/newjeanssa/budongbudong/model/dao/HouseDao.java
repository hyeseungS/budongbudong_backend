package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.house.HouseDealDto;
import com.newjeanssa.budongbudong.model.dto.house.HouseDealRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseDao {
    List<HouseDealDto> findHouseDeals(HouseDealRequest houseDealRequest);
}
