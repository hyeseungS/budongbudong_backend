package com.newjeanssa.budongbudong.model.service.house;

import com.newjeanssa.budongbudong.model.dao.HouseDao;
import com.newjeanssa.budongbudong.model.dto.house.HouseDealDto;
import com.newjeanssa.budongbudong.model.dto.house.HouseDealRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseDao houseDao;

    /*
        동 이름, 년도, 월로 실거래가 검색
     */
    public List<HouseDealDto> findHouseDeals(HouseDealRequest houseDealRequest) {
        return houseDao.findHouseDeals(houseDealRequest);
    }
}
