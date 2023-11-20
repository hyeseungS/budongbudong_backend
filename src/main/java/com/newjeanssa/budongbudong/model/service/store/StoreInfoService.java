package com.newjeanssa.budongbudong.model.service.store;

import com.newjeanssa.budongbudong.model.dao.StoreInfoDao;
import com.newjeanssa.budongbudong.model.dto.store.StoreInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreInfoService {
    private final StoreInfoDao storeInfoDao;
    /*
    상권정보 조회
     */
    public List<StoreInfoResponseDto> getStoreInfos(String categoryCode) {
        return storeInfoDao.selectByCategoryCode(categoryCode);
    }
}
