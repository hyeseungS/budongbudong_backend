package com.newjeanssa.budongbudong.model.service.scrap;

import com.newjeanssa.budongbudong.model.dao.ScrapDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.scrap.ScrapResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapDao scrapDao;
    /*
    관심매물 등록
     */
    public void registerScrap(Optional<UserDto> userDto, long aptRealTransId) {
        scrapDao.insertScrap(userDto.get().getId(), aptRealTransId);
    }

    /*
    관심매물 전체 조회
     */
    public List<ScrapResponseDto> getScraps(Optional<UserDto> userDto) {
        return scrapDao.selectScraps(userDto.get().getId());
    }

    /*
    관심매물 여부 조회
     */
    public boolean isScrap(Optional<UserDto> userDto, long aptRealTransId) {
        Optional<Long> scrap = scrapDao.selectScrap(userDto.get().getId(), aptRealTransId);
        return scrap.isPresent();
    }

    /*
    관심매물 삭제
     */
    public void removeScrap(Optional<UserDto> userDto, long aptRealTransId) {
        scrapDao.deleteScrap(userDto.get().getId(), aptRealTransId);
    }

}
