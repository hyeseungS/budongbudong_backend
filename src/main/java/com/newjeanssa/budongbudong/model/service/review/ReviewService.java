package com.newjeanssa.budongbudong.model.service.review;

import com.newjeanssa.budongbudong.model.dao.ReviewDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.review.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;

    /*
    리뷰 등록
     */
    @Transactional
    public void registerReview(Optional<UserDto> userDto, ReviewRegisterRequest reviewRegisterRequest) {
        ReviewAptDto reviewDto = ReviewAptDto.builder()
                .reviewComment(reviewRegisterRequest.getReviewComment())
                .reviewScore(reviewRegisterRequest.getReviewScore())
                .aptId(reviewRegisterRequest.getAptId())
                .build();
        userDto.ifPresent(reviewDto::setUserDto);
        reviewDao.insertReview(reviewDto);
        reviewDao.updateReviewScore(reviewRegisterRequest.getAptId());
    }

    /*
    아파트 아이디로 리뷰 전체 조회
     */
    public List<ReviewAptDto> getReviewsByAptId(String aptId) {
        return reviewDao.selectReviewsByAptId(aptId);
    }

    /*
    회원 아이디로 리뷰 전체 조회
     */
    public List<ReviewUserDto> getReviewsByuserId(Optional<UserDto> userDto) {
        return reviewDao.selectReviewsByUserId(userDto.get().getId());
    }

    /*
    리뷰 아이디로 리뷰 상세 조회
     */
    public Optional<ReviewIdsDto> getReview(Long reviewId) {
        return reviewDao.selectReview(reviewId);
    }

    /*
    리뷰 수정
     */
    @Transactional
    public void modifyReview(Optional<UserDto> userDto, String aptId, ReviewUpdateRequest reviewUpdateRequest) {
        ReviewAptDto reviewDto = ReviewAptDto.builder()
                .aptId(aptId)
                .reviewComment(reviewUpdateRequest.getReviewComment())
                .reviewScore(reviewUpdateRequest.getReviewScore())
                .reviewId(reviewUpdateRequest.getReviewId())
                .build();
        userDto.ifPresent(reviewDto::setUserDto);
        reviewDao.updateReview(reviewDto);
        reviewDao.updateReviewScore(reviewDto.getAptId());
    }

    /*
    리뷰 삭제
     */
    @Transactional
    public void removeReview(ReviewIdsDto reviewIdsDto) {

        reviewDao.deleteReview(reviewIdsDto.getReviewId());
        reviewDao.updateReviewScore(reviewIdsDto.getAptId());
    }
}
