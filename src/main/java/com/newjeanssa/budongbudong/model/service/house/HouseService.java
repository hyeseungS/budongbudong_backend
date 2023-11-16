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
    public List<AptCodeDto> findHouseList(HouseRequest houseRequest) {
        return houseDao.findHouseList(houseRequest);
    }

    /*
    아파트 코드로 조회
     */
    @Transactional
    public AptDto findHouseDetail(String aptCode) {
        houseDao.updateHit(aptCode);
        AptDto aptDto = houseDao.findHouseDetail(aptCode);
//        List<AptAreaDto> aptAreaDtoList = houseDao.findAreaList(aptCode);
//        aptDto.setAptAreaList(aptAreaDtoList);
        return aptDto;
    }

    /*
    비교분석 : 매물 id로 조회
     */
    public List<AptDto> findHouseCompareList(List<String> dealList) {
        //return houseDao.findHouseCompareList(dealList);
        return null;
    }

}
