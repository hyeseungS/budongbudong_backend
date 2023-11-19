package com.newjeanssa.budongbudong.model.service.review;

import com.newjeanssa.budongbudong.model.dao.ReviewDao;
import com.newjeanssa.budongbudong.model.dto.auth.UserDto;
import com.newjeanssa.budongbudong.model.dto.house.AptDto;
import com.newjeanssa.budongbudong.model.dto.review.ReviewDto;
import com.newjeanssa.budongbudong.model.dto.review.ReviewRegisterRequest;
import com.newjeanssa.budongbudong.model.dto.review.ReviewUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void registerReview(Optional<UserDto> userDto, ReviewRegisterRequest reviewRegisterRequest) {
        AptDto aptDto = AptDto.builder().aptId(reviewRegisterRequest.getAptId()).build();
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewComment(reviewRegisterRequest.getReviewComment())
                .reviewScore(reviewRegisterRequest.getReviewScore())
                .aptDto(aptDto)
                .build();
        userDto.ifPresent(reviewDto::setUserDto);
        reviewDao.insertReview(reviewDto);
    }

    /*
    아파트 아이디로 리뷰 전체 조회
     */
    public List<ReviewDto> getReviewsByAptId(String aptId) {
        return reviewDao.selectReviewsByAptId(aptId);
    }

    /*
    회원 아이디로 리뷰 전체 조회
     */
    public List<ReviewDto> getReviewsByuserId(Optional<UserDto> userDto) {
        return reviewDao.selectReviewsByUserId(userDto.get().getId());
    }

    /*
    리뷰 아이디로 리뷰 상세 조회
     */
    public Optional<ReviewDto> getReview(Long reviewId) {
        return reviewDao.selectReview(reviewId);
    }

    /*
    리뷰 수정
     */
    public void modifyReview(Optional<UserDto> userDto, ReviewUpdateRequest reviewUpdateRequest) {
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewComment(reviewUpdateRequest.getReviewComment())
                .reviewScore(reviewUpdateRequest.getReviewScore())
                .reviewId(reviewUpdateRequest.getReviewId())
                .build();
        userDto.ifPresent(reviewDto::setUserDto);
        reviewDao.updateReview(reviewDto);
    }

    /*
    리뷰 삭제
     */
    public void removeReview(Long reviewId) {
        reviewDao.deleteReview(reviewId);
    }
}
