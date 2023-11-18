package com.newjeanssa.budongbudong.model.service.house;

import com.newjeanssa.budongbudong.model.dao.HouseDao;
import com.newjeanssa.budongbudong.model.dto.house.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseDao houseDao;

    /*
        시도, 구군, 동 검색
     */
    public List<AptCodeDto> getHouseList(HouseRequest houseRequest) {
        return houseDao.selectHouseList(houseRequest);
    }

    /*
    아파트 코드로 조회
     */
    @Transactional
    public AptDto getHouseDetail(String aptCode) {
        houseDao.updateHit(aptCode);
        return houseDao.selectHouseDetail(aptCode);
    }

    /*
    비교분석 : 매물 id로 조회
     */
    public List<AptDto> getHouseCompareList(List<Integer> dealList) {
        return houseDao.selectHouseCompareList(dealList);
    }

}
