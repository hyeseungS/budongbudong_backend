package com.newjeanssa.budongbudong.model.dao;

import com.newjeanssa.budongbudong.model.dto.top.TopDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TopDao {
    List<TopDto> selectTop();
    List<TopDto> selectTopScrap(String dongName);
}
