package com.newjeanssa.budongbudong.model.service.store;

import com.newjeanssa.budongbudong.model.dao.StoreInfoDao;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import com.newjeanssa.budongbudong.model.dto.store.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreInfoService {
    private final StoreInfoDao storeInfoDao;
    /*
    상권정보 조회
     */
    public List<StoreInfoResponseDto> getStoreInfos(String dongCode, String categoryCode) {
        StoreInfoRequestDto storeInfoRequestDto = StoreInfoRequestDto.builder().dongCode(dongCode).categoryCode(categoryCode).build();
        return storeInfoDao.selectByCategoryCode(storeInfoRequestDto);
    }
    /*
    점수 update
     */
    @Transactional
    public void modifyScore(List<AptDto> aptDtoList) {
        double[] weights = {1.0, 0.8, 0.5}; // 가중치
        int[][] distances = {{0, 200}, {200, 400}, {400, 600}}; // 반경
        String[] codes = {"10", "20", "30", "40", "50"}; // 카테고리 코드 (종합소매, 음식, 교육, 보건 의료, 편의)
        for (AptDto aptDto : aptDtoList) {
            StoreDistanceDto storeDistanceDto = StoreDistanceDto.builder()
                    .dongCode(aptDto.getDongCode())
                    .lat(Double.parseDouble(aptDto.getLat()))
                    .lng(Double.parseDouble(aptDto.getLng())).build();
            double[] scores = new double[5];
            for (int i = 0; i < weights.length; i++) { // 거리별로
                storeDistanceDto.setStart(distances[i][0]);
                storeDistanceDto.setEnd(distances[i][1]);
                for (int j = 0; j < 5; j++) { // 카테고리별로
                    storeDistanceDto.setCategoryCode(codes[j]);
                    scores[j] += storeInfoDao.getDistanceCount(storeDistanceDto).size() * weights[i];
                    if(scores[j] > 10) scores[j] = 10;
                }
            }
            double totalScore = 0;
            for (int i = 0; i < 5; i++) {
                scores[i] = (scores[i] / 10) * 100;
                totalScore += scores[i];
            }
            ScoreDto scoreDto = ScoreDto.builder()
                    .aptId(aptDto.getAptId())
                    .totalScore(totalScore / 5)
                    .storeScore(scores[0])
                    .foodScore(scores[1])
                    .educationScore(scores[2])
                    .medicalScore(scores[3])
                    .facilitiesScore(scores[4])
                    .build();
            storeInfoDao.updateStoreScore(scoreDto); // 점수 설정
        }
    }
}
