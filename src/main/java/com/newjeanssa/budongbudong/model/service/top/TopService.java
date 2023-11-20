package com.newjeanssa.budongbudong.model.service.top;

import com.newjeanssa.budongbudong.model.dao.ScrapDao;
import com.newjeanssa.budongbudong.model.dao.TopDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.top.TopDto;
import com.newjeanssa.budongbudong.model.dto.top.TopResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopService {

    private final TopDao topDao;
    private final ScrapDao scrapDao;

    /*
    Top 조회순 5개 조회
     */
    public List<TopDto> getTop() {
        return topDao.selectTop();
    }

    /*
    회원 관심 매물 중 랜덤 동 Top 조회순 5개 조회
     */
    public TopResponseDto getTopScrap(Optional<UserDto> userDto) {
        List<String> dongNames = scrapDao.selectDongNames(userDto.get().getId());
        if(dongNames.isEmpty()) { // 관심매물 없을 경우 null 반환
            return null;
        }
        // 회원 관심 매물 중 랜덤 동 선택하기
        Random random = new Random(); // 랜덤 객체 생성(디폴트 시드값 : 현재시간)
        String randDongName = dongNames.get(random.nextInt(dongNames.size()));
        List<TopDto> topDto = topDao.selectTopScrap(randDongName);
        return TopResponseDto.builder()
                                                    .dongName(randDongName)
                                                    .topDtoList(topDto)
                                                    .build();
    }

}
